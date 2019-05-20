<!DOCTYPE html>

<?php
	session_start();
	if (empty($_SESSION['doc_name']) || empty($_SESSION['user']) || empty($_SESSION['doc_id']))
	{
		sleep(3);
		session_destroy();
		header("location: index.php");
	}
?>

		
<?php
	if($_POST)
	{
		if (isset($_POST["name"]) && isset($_POST["username"]) && isset($_POST["oldpassword"]) && isset($_POST["newpassword"]) && isset($_POST["repassword"])){
			$DBServer= mysqli_connect('localhost', 'root', '') or die('Connection failed!');
            mysqli_select_db($DBServer,'doctors') or die('Database Connect Error!');
            // how to read data from database
            $new_query= "SELECT * FROM doctors WHERE doctorid='" . $_SESSION['doc_id'] . "'";
            $data= mysqli_query($DBServer, $new_query) or die('Read ERROR');
            $num_records= mysqli_num_rows($data);
			if($num_records > 0){
				$rec = mysqli_fetch_array($data);
				$pass = $rec['pass'];
				if (md5($_POST["oldpassword"]) == $pass && $_POST["newpassword"] == $_POST["repassword"])
				{
					$new_query="UPDATE doctors SET doctorname='" . $_POST["name"] . "',uname='" . $_POST["username"] . "',pass='" .  md5($_POST["newpassword"]) . "' WHERE doctorid='" . $_SESSION['doc_id'] . "'";
					$data= mysqli_query($DBServer, $new_query) or die('Read ERROR');
					mysqli_close($DBServer);
					$_SESSION['doc_name'] = $_POST["name"];
					$_SESSION['user'] = $_POST["username"];
					header("location: admin.php");
				}
			}
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
    <td colspan="3" style="font-size:32px; font-family:Arial; color:#FFFFFF; background-color:rgb(91,201,149); padding-left: 10px;">Update Profile</td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">Name: <input type="text" name="name" value="<?php echo $_SESSION['doc_name'] ?>"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">Username: <input type="text" name="username" value="<?php echo $_SESSION['user'] ?>"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">Old Password: <input type="password" name="oldpassword" value=""></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">New Password: <input type="password" name="newpassword"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;">Retype new password: <input type="password" name="repassword"></td>
  </tr>
  <tr style="height:40px; background-color:#999999;">
	<td style="width:20%; font-family:Arial; font-size:18px; padding-left: 10px;"><input type="submit" value="Update Profile"></td>
  </tr>

</table>
</form>
 </main>
  



</body>

</html>
