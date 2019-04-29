<?php
	$servername = "yourservername";
	$username = "dbusername";
	$password = "dbpassword";
	$dbname = "dbname";
	
	$conn = new mysqli($servername, $username, $password, $dbname);
	if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
	}
	$query = mysqli_query($conn,"SELECT * FROM datas");

	// Create empty array to hold query results
	$someArray = array();

	// Loop through query and push results into $someArray;
	while ($row = mysqli_fetch_assoc($query)) {
		$obj = null;
		$obj->date = $row["date"];
		$obj->value = $row["value"];
		array_push($someArray, $obj);
	}
	
	$someArray = array_reverse($someArray);
	
	// Convert the Array to a JSON String and echo it
	$someJSON = json_encode($someArray);
	$conn->close();
	
	echo $someJSON;
?>