<?php
	$servername = "yourservername";
	$username = "dbusername";
	$password = "dbpassword";
	$dbname = "dbname";
	
	$date = date("d-m-Y H:i:s");
	
	if($_POST['data']){
		$data = $_POST['data'];
		$conn = new mysqli($servername, $username, $password, $dbname);
		if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
		}
		$sql = "INSERT INTO datas (date, value) VALUES ('$date', '$data')";
		if ($conn->query($sql) === TRUE) {
			echo "New record created successfully";
		} else {
			echo "Error: " . $sql . "<br>" . $conn->error;
		}
		$conn->close();
	}
	else{
		echo 'No data income.';
	}
?>