Project Name: Healthcare Follow-Up

Description: It includes of an android application which provides to follow-up simultaneously patients' vital 
parameters such as ECG, Pulse and SpO2 by individuals and a prototype which provides to take vital 
parameters from the patient's body will be developed.It is aimed to monitor the irregularities in the health 
status of the patient or individual and to inform the individual about his / her health.

Needed Materials To Test:
	
	1-) Cloud Server
	2-)	Android Studio
	3-)	Filezilla or any FTP Software
	4-)	ESP01 Wifi Module
	5-)	Arduino UNO Rev3
	6-)	AD8232 ECG Sensor
	7-)	MAX30100 SpO2 Sensor
	8-) Mobile Phone or Emulator(Genymotion)
	
If you have the all the materials, here is the steps:
	
	1-) Connect your cloud server via FTP and upload Files in "/ASP.Net Webservice/Files" folder.
	2-) If you need to edit namespace on webservices, open project files in "/ASP.Net Webservice/ASP.Net Webservice.sln"
		via Visual Studio. Save the project and publish to your server.
	3-) Edit the ssid,password,server and url variables in arduino.ino file and save.
	4-) Look at Readme[connections] file and connect ESP01 wifi module and your sensors to Arduino by the help of jumper cables.
	5-) Upload arduino.ino file to your Arduino.
	6-) After, Arduino works fine. Try to login system. 
	7-) Open Android Studio and debug it.
