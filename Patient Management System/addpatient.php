<!DOCTYPE html>

<?php
	session_start();
	if (empty($_SESSION['doc_name']) || empty($_SESSION['user']))
	{
		sleep(3);
		session_destroy();
		header("location: index.php");
	}
?>
<?php 
	if($_POST)
	{
		if (isset($_POST["name"]) && isset($_POST["surname"]) && isset($_POST["personalid"]) && isset($_POST["insurance"]) && isset($_POST["housenumber"])
			&& isset($_POST["street"]) && isset($_POST["zipcode"]) && isset($_POST["city"]) && isset($_POST["phonenumber"]) && isset($_POST["gender"])) {
			$DBServer= mysqli_connect('localhost', 'root', '') or die('Connection failed!');
            mysqli_select_db($DBServer,'patients') or die('Database Connect Error!');
            // how to read data from database
            $new_query= "INSERT INTO patientinfo (doctorid,name,surname,personalid,insurance,housenumber,street,zipcode,city,phonenumber,gender) VALUES ('" . $_SESSION['doc_id'] . "','" . $_POST["name"] . "','" . $_POST["surname"]
			. "','" . $_POST["personalid"] . "','" . $_POST["insurance"] . "','" . $_POST["housenumber"] . "','" . $_POST["street"] . "','" . $_POST["zipcode"] . "','" . $_POST["city"] . "','" . $_POST["phonenumber"] . "','" . $_POST["gender"] . "')";
            $data= mysqli_query($DBServer, $new_query) or die('Read ERROR');
			$lastID = mysqli_insert_id($DBServer);
			$queryfordata = "CREATE TABLE patient" . $lastID ." (id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
				date DATETIME NOT NULL,
				heartrate FLOAT NOT NULL,
				spo2 FLOAT NOT NULL,
				temp FLOAT NOT NULL)";
			$pat = mysqli_query($DBServer,$queryfordata) or die('Read ERROR');
            mysqli_close($DBServer);
			header("location: yourpatients.php");
		}
	}


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

<main>
<form action="" method="POST">
<table style="width:500px; margin:50px auto; border-collapse:collapse;">
  <tr style=" height:40px;">
    <td colspan="3" style="font-size:32px; font-family:Arial; color:#FFFFFF; background-color:rgb(91,201,149); padding-left: 10px;">Add Patient</td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">Name: <input type="text" name="name"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">Surname: <input type="text" name="surname"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">Personal ID: <input type="text" name="personalid"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">Insurance Company: <input type="text" name="insurance"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">House Number: <input type="text" name="housenumber"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">Street: <input type="text" name="street"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">Zipcode: <input type="text" name="zipcode"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">City: <input type="text" name="city"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">Phone Number: <input type="text" name="phonenumber"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">Gender: <input type="text" name="gender"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
	<td style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;"><input type="submit" value="Add Patient"></td>
  </tr>

</table>
</form>
 </main>
  



</body>

</html>
