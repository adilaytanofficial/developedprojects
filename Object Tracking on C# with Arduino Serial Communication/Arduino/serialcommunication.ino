int ledarray[9] = { 3, 4, 5, 6, 7, 8, 9, 10, 11}; 
int cur;
void setup() {
   Serial.begin(9600);
   cur = 0;
   for(int i=0; i < sizeof(ledarray); i++){
      pinMode(i,OUTPUT);
   }
}

void loop() {
    String rd = "";
    if (Serial.available() > 0){
      rd = Serial.readString();
      if (rd != "null")
      {
         switch_led(rd);
      }
      else 
      {
        digitalWrite(cur,LOW);
      }
    }
}

void switch_led(String led){
  int cur_led = ((String)led[3]).toInt() - 1;
  for (int i=0; i < sizeof(ledarray); i++){
        if ( i != cur_led) 
        {
          digitalWrite(ledarray[i],LOW); 
        }
        else
        {
          digitalWrite(ledarray[i],HIGH); 
          cur = ledarray[i];
        }
   }
}


