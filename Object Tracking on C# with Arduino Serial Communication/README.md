Project Name: Object Tracking on C# with Arduino Serial Commmunication

Description: This project allows to tracking object according to color which you choose in program.
If the object,which has a same color with you choose,is in the camera's view, the object is detected then
its location determined, and rectangle drawen in the screen.There are 9 area in the screen, according to
object's area on the screen, the relevant led is turned on by arduino(serial communication).

Needed materials:
		1-) Laptop or PC
		2-) Camera (if you have laptop, you don't need it)
		3-) You have need 9 LED
		4-) 9 X 330 ohm Resistor
		5-) Arduino Uno Rev3
		6-) Jumper Cables
		
If you have the all the materials, here is the steps:

	1-) Look at Readme[connections] file and connect all leds and resistors to Arduino by the help of jumper cables.
	2-) Upload serialcommunication.ino file to your Arduino.
	3-) Open the "Object Tracking Csharp" and debug it.
	4-) Play with R,G,B track to get your color.
	5-) Select your camera and Click "Select Cam".
	6-) Select your port and Click "Select Port".
	7-) Let's test it.
	8-) Move your object in camera's view and check the which led is turned on.
