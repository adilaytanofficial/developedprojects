<?php
	if($_POST){
		if (isset($_POST["patid"])){
				$DBServer= mysqli_connect('localhost', 'root', '') or die('Connection failed!');
				mysqli_select_db($DBServer,'patients') or die('Database Connect Error!');
				$patid = $_POST["patid"];
				$query = "SELECT * FROM patient" . $patid . " ORDER BY date DESC";
				$data= mysqli_query($DBServer, $query) or die('Datas couldn\'t read from database.');
				$rec = mysqli_num_rows($data);
				if ($rec > 0){
					$results = (array) null;
					for ($i=1; $i<=$rec; $i++){
						$fetch = mysqli_fetch_array($data);
						$obj = null;
						$obj -> patid = $patid;
						$obj -> date = $fetch['date'];
						$obj -> heartrate = $fetch['heartrate'];
						$obj -> spo2 = $fetch['spo2'];
						$obj -> temp = $fetch['temp'];
						array_push($results,$obj);
					}
				}
				$out = json_encode($results);
				mysqli_close($DBServer);
				echo $out;
		}
	}
?>