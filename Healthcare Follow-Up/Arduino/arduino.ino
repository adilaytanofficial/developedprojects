#include <SoftwareSerial.h>
#include <Wire.h>
#include <SPI.h>
#include "MAX30100_PulseOximeter.h"

#define REPORTING_PERIOD_MS     1000
PulseOximeter pox;
uint32_t tsLastReport = 0;
int count=0;
float npulse;
float average;
float sturas;

void connectWifi();

String ssid = "yourssid";
String password = "yourpassword";

String server = "yoursite.com";
String url = "/addvalues.asmx/addVal";
String ecg_url = "/addvalues.asmx/addECG";

SoftwareSerial esp(6,5); // TX | RX

String data;
String ecg_data;
String ANS="";
String patid = "47";
String temp;
String pulse;
String spo2value;


void connectWifi() {
    String cmd = "AT+CWJAP=\"" +ssid+"\",\"" + password + "\"";
    esp.println(cmd);
    delay(4000);
    if(esp.find("OK")) {
      Serial.println("Connected!");
    }
}
void onBeatDetected()
{
    count++;// counter for average
}

void setup()
{
    Serial.begin(9600);
    pinMode(10, INPUT); // Setup for leads off detection LO +
    pinMode(11, INPUT); // Setup for leads off detection LO -
    esp.begin(9600);
    esp.println("AT+CWMODE=1");
    delay(1000);
    connectWifi();
    pox.begin();
    pox.setOnBeatDetectedCallback(onBeatDetected);
}

void loop()
{
    while(true){
        pox.update();
        sturas = pox.getSpO2();

        if (millis() - tsLastReport > REPORTING_PERIOD_MS) {
           for(int i=0;i<10;i++){average=average+pox.getHeartRate();}
              average=average/10;  
              npulse=npulse+average;
              average=0; 
           if(count>4){
                  npulse=npulse/3;
                  String pulse = String(npulse);
                  String spo2value = String(sturas);
                  delay(3000);
                  npulse=0;
                  count=0;
                  break;
            }
            tsLastReport = millis();
          } 
        }       
        for (int i=0;i<20;i++){
                 ecg_data += analogRead(A0);
                 ecg_data +=",";
        }
		ecg_data = "&ecg=" + String(ecg_data);
		postecg ();
        delay(500);
        ecg_data = "";
        data="patid=" + patid + "&pulse=" + String(pulse) + "&spo2=" + String(spo2value);
        postdata();
}
void postecg () 
{
      esp.println("AT+CIPSTART=\"TCP\",\"" + server + "\",80");//start a TCP connection.
      if( esp.find("OK")) {
        Serial.println("TCP connection ready");
      } 
      delay(1000);
      
      String postRequest = "POST " + ecg_url + " HTTP/1.0\r\n" + "Host: " + server + "\r\n" + "Accept: *" + "/" + "*\r\n" + "Content-Length: " + ecg_data.length() + "\r\n" +
      "Content-Type: application/x-www-form-urlencoded\r\n" + "\r\n" + ecg_data;
      
      String sendCmd = "AT+CIPSEND=";//determine the number of caracters to be sent.
      esp.print(sendCmd);
      esp.println(postRequest.length());
      delay(500);
      while(true)
      {
         if (esp.find(">"))
         {
            esp.print(postRequest);
            if(esp.find("SEND OK")) { 
              Serial.println("Packet sent");
              break;
            }
         }
      }
      esp.println("AT+CIPCLOSE");        
}

void postdata() 
{
      esp.println("AT+CIPSTART=\"TCP\",\"" + server + "\",80");//start a TCP connection.
      if( esp.find("OK")) {
        Serial.println("TCP connection ready");
      } 
      delay(1000);
      
      String postRequest = "POST " + url + " HTTP/1.0\r\n" + "Host: " + server + "\r\n" + "Accept: *" + "/" + "*\r\n" + "Content-Length: " + data.length() + "\r\n" +
      "Content-Type: application/x-www-form-urlencoded\r\n" + "\r\n" + data;
      
      String sendCmd = "AT+CIPSEND=";//determine the number of caracters to be sent.
      esp.print(sendCmd);
      esp.println(postRequest.length());
      delay(500);
      while(true)
      {
         if (esp.find(">"))
         {
            esp.print(postRequest);
            if(esp.find("SEND OK")) { 
              Serial.println("Packet sent");
              break;
            }
         }
      }
      esp.println("AT+CIPCLOSE");        
}