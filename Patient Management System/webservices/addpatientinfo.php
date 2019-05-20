<?php
	if($_POST){
		if (isset($_POST["patid"]) && isset($_POST["date"]) && isset($_POST["heartrate"]) && isset($_POST["spo2"]) && isset($_POST["temp"])){
				$DBServer= mysqli_connect('localhost', 'root', '') or die('Connection failed!');
				mysqli_select_db($DBServer,'patients') or die('Database Connect Error!');
				$patid = $_POST["patid"];
				$query = "INSERT INTO patient" . $patid . " (date,heartrate,spo2,temp) VALUES ('" . $_POST["date"] . "','" . $_POST["heartrate"] . "','" . $_POST["spo2"] . "','" . $_POST["temp"] . "')";
				$data= mysqli_query($DBServer, $query) or die('Record couldn\'t inserted to database.');
				mysqli_close($DBServer);
		}
	}
?>