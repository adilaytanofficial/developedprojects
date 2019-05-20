<!DOCTYPE html>

<?php
	session_start();
	if ($_GET){
		if (!isset($_GET['patid'])){
			sleep(3);
			header("location: index.php");
		}
	}
	if (empty($_SESSION['doc_name']) || empty($_SESSION['user']))
	{
		sleep(3);
		header("location: index.php");
	}
	//echo 'Daha önceden kaydedilmiţ isim: ' . $_SESSION['user'];
	//$doc = ;
?>
<html lang="en" >

<head>
  <meta charset="windows-1250">
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
            $new_query="SELECT * FROM patientinfo where patientid='" . $_GET['patid'] . "'";
            $data= mysqli_query($DBServer, $new_query) or die('Read ERROR');
            $rec= mysqli_fetch_array($data);
			$num_records= mysqli_num_rows($data);
			$patid= $_GET['patid'];
			if ($num_records > 0){
				echo '<table style="width: 600px; background-color: white; border-collapse: collapse; margin: 10%;" border="1">';
				echo '<tr>';
				echo '<td colspan="2" style="color: black; font-weight: bold; text-align: center;">Patient ID: '; echo $patid; echo '</td>';
				echo '</tr>';
				echo '<tr>';
				echo '<td style="color: black; font-weight: bold; text-align: left;">Doctor ID</td>';
				echo '<td style="color: black;">' . $rec['patientid'] . '</td>';
				echo '</tr>';
				echo '<tr>';
				echo '<td style="color: black; font-weight: bold; text-align: left;">Doctor Name</td>';
				echo '<td style="color: black;">' . $_SESSION['doc_name'] . '</td>';
				echo '</tr>';
				echo '<tr>';
				echo '<td style="color: black; font-weight: bold; text-align: left;">Name</td>';
				echo '<td style="color: black;">' . $rec['name'] . '</td>';
				echo '</tr>';
				echo '<tr>';
				echo '<td style="color: black; font-weight: bold; text-align: left;">Surname</td>';
				echo '<td style="color: black;">' . $rec['surname'] . '</td>';
				echo '</tr>';
				echo '<td style="color: black; font-weight: bold; text-align: left;">Personal ID</td>';
				echo '<td style="color: black;">' . $rec['personalid'] . '</td>';
				echo '</tr>';
				echo '<td style="color: black; font-weight: bold; text-align: left;">Insurance Company</td>';
				echo '<td style="color: black;">' . $rec['insurance'] . '</td>';
				echo '</tr>';
				echo '<td style="color: black; font-weight: bold; text-align: left;">House Number</td>';
				echo '<td style="color: black;">' . $rec['housenumber'] . '</td>';
				echo '</tr>';
				echo '<td style="color: black; font-weight: bold; text-align: left;">Street</td>';
				echo '<td style="color: black;">' . $rec['street'] . '</td>';
				echo '</tr>';
				echo '<td style="color: black; font-weight: bold; text-align: left;">Zipcode</td>';
				echo '<td style="color: black;">' . $rec['zipcode'] . '</td>';
				echo '</tr>';
				echo '<td style="color: black; font-weight: bold; text-align: left;">City</td>';
				echo '<td style="color: black;">' . $rec['city'] . '</td>';
				echo '</tr>';
				echo '<td style="color: black; font-weight: bold; text-align: left;">Phone Number</td>';
				echo '<td style="color: black;">' . $rec['phonenumber'] . '</td>';
				echo '</tr>';
				echo '<td style="color: black; font-weight: bold; text-align: left;">Gender</td>';
				echo '<td style="color: black;">' . $rec['gender'] . '</td>';
				echo '</tr>';
			}
			else
			{
				sleep(1);
				header("location: admin.php");
			}
           
        ?>
	</table>
	
</div>

  



</body>

</html>
