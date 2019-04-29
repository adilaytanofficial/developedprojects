Project Name: Arduino Send Sensor Data to Cloud Database

Description: This project allows to send sensor's data to cloud database with date.
Firstly, you need to these materials:
	1-) Cloud Server
	2-) Mysql Database
	3-) Arduino UNO Rev3
	4-) ESP01 Wifi Module
	5-) Jumper Cables
	6-) Any sensor (in this project, i just used a sensor connected to A0 pin)

If you have the all the materials, here is the steps:
	
	1-) Connect your server via FTP and upload PHP scripts.
	2-) Create a mysql databes and in this database create a table called "datas" (has two column: date and value).
	3-) Edit the servername,username,password and dbname variables in PHP scripts and save them.
	3-) Edit the ssid,password,server and url variables in cloud.ino file and save.
	4-) Look at Readme[connections] file and connect ESP01 wifi module and your sensor to Arduino by the help of jumper cables.
	5-) Upload cloud.ino file to your Arduino.
	6-) Let's test it.