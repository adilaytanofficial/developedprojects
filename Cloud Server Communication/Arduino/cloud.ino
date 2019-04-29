#include <SoftwareSerial.h>

/*  Variables */
String ssid = "yourssid";
String password = "wifipassword";
String server = "servername";
String url = "/receiver.php";
String data;

/* Methods */
void wifiConnection();
void sendData();

/* Connection for ESP Module */
SoftwareSerial esp(6,5); // ESP TX | ESP RX


void wifiConnection() {
    String cmd = "AT+CWJAP=\"" +ssid+"\",\"" + password + "\"";
    esp.println(cmd);
    delay(4000);
    if(esp.find("OK")) {
      Serial.println("Connected!");
    }
}

void setup()
{
    Serial.begin(9600);
    esp.begin(9600);
    esp.println("AT+CWMODE=1");
    delay(1000);
    wifiConnection(); 
}

void loop()
{
    int measurement = analogRead(A0);
    data="data=" + measurement;
    sendData();
    delay(1000);
}

void sendData() 
{
      esp.println("AT+CIPSTART=\"TCP\",\"" + server + "\",80");//start a TCP connection.
      if( esp.find("OK")) {
        Serial.println("TCP connection is ready");
      } 
      delay(1000);
      
      String postRequest = "POST " + url + " HTTP/1.0\r\n" + "Host: " + server + "\r\n" + "Accept: *" + "/" + "*\r\n" + "Content-Length: " + data.length() + "\r\n" +
      "Content-Type: application/x-www-form-urlencoded\r\n" + "\r\n" + data;
      
      String sendCmd = "AT+CIPSEND=";
      esp.print(sendCmd);
      esp.println(postRequest.length());
      delay(500);
      while(true)
      {
         if (esp.find(">"))
         {
            esp.print(postRequest);
            if(esp.find("SEND OK")) { 
              Serial.println("Your datas are sent to server.");
              break;
            }
         }
      }
      esp.println("AT+CIPCLOSE");        
}
