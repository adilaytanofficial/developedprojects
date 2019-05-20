<!DOCTYPE html>

<?php
	session_start();
	if (empty($_SESSION['doc_name']) || empty($_SESSION['user']))
	{
		sleep(3);
		header("location: index.php");
	}
	//echo 'Daha önceden kaydedilmiþ isim: ' . $_SESSION['user'];
	//$doc = ;
?>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Doctor : <?php echo $_SESSION['doc_name']; ?></title>
  <link rel="stylesheet" href="css/style.css">

  
</head>

<body>

  <nav class="menu" tabindex="0">
	<div class="smartphone-menu-trigger"></div>
  <header class="avatar">
		<a href="admin.php"><img src="images/doctor.png" /></a>
    <h2><?php echo $_SESSION['doc_name']; ?></h2>
  </header>
	<ul>
    <li tabindex="0" class="icon-users"><a href="yourpatients.php" style="text-decoration:none; color: white;"><span>Your Patients</span></a></li>
    <li tabindex="0" class="icon-addusers"><a href="addpatient.php" style="text-decoration:none; color: white;"><span>Add Patient</span></a></li>
	<li tabindex="0" class="icon-status"><a href="patientstatus.php" style="text-decoration:none; color: white;"><span>Patients Status</span></a></li>
    <li tabindex="0" class="icon-profile"><a href="updateprofile.php" style="text-decoration:none; color: white;"><span>Update Profile</span></a></li>
    <li tabindex="0" class="icon-logout"><a href="logout.php" style="text-decoration:none; color: white;"><span>Log-Out</span></a></li>
  </ul>
</nav>


<div>
	
		<?php 
            // connection to database
            $DBServer= mysqli_connect('localhost', 'root', '') or die('Connection failed!');
            mysqli_select_db($DBServer,'patients') or die('Database Connect Error!');
            // how to read data from database
            $new_query="SELECT * FROM patientinfo WHERE doctorid='" . $_SESSION['doc_id'] . "'";
            $data= mysqli_query($DBServer, $new_query) or die('Read ERROR');
            $num_records= mysqli_num_rows($data);
			if ($num_records > 0)
			{
				echo '<table style="width: 600px; background-color: white; border-collapse: collapse; margin: 10%;" border="1">';
				echo '<tr>';
				echo '<td style="color: black; font-weight: bold; text-align: center;">Patient ID</td>';
				echo '<td style="color: black; font-weight: bold; text-align: center;">Doctor Name</td>';
				echo '<td style="color: black; font-weight: bold; text-align: center;">Name</td>';
				echo '<td style="color: black; font-weight: bold; text-align: center;">Surname</td>';
				echo '<td style="color: black; font-weight: bold; text-align: center;">Details</td>';
				echo '</tr>';
				for($i=1; $i<=$num_records; $i++)
				{
					$rec= mysqli_fetch_array($data);
					echo '<tr>';
					echo '<td style="color: black;">' . $rec['patientid'] . '</td>';
					echo '<td style="color: black;">' . $_SESSION['doc_name'] . '</td>';
					echo '<td style="color: black;">' . $rec['name'] . '</td>';
					echo '<td style="color: black;">' . $rec['surname'] . '</td>';
					echo '<td style="color: black;">' . '<a href="patients.php?patid=' . $rec['patientid'] . '">See Details</a></td>';
					echo '</tr>';
				}
				echo '</table>';
			}
			else
			{
				echo '<main><div class="helper">You don\'t have any patient...</div></main>';
			}
        ?>
</div>

  



</body>

</html>
