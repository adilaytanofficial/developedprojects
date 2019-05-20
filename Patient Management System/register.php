<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Register Form</title>
</head>



<?php
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "doctors";

	if ($_POST){
		if($_POST["name"] != "" && $_POST["username"] != "" && $_POST["password"] != "" && $_POST["repassword"] != "" && ($_POST["password"] == $_POST["repassword"])){
			$conn = new mysqli($servername, $username, $password, $dbname);
			if (!($conn->connect_error)) { //eðer baðlandýysa
				$r_name=$_POST["name"];
				$un = $_POST["username"];
				$un_p= $_POST["password"];
				$pw = md5($un_p);
				$sql_query = "INSERT INTO doctors (doctorname,uname, pass) VALUES ('$r_name','$un', '$pw')";
				if ($conn->query($sql_query) === TRUE) // query dogru islediyses
				{
					sleep(30);
					header("location: index.php");
				} 
				else 
				{
					echo '<script language="javascript">';
					echo 'alert("Something went wrong, try again...")';
					echo '</script>';
				}
				$conn->close();
			}
			else
			{
				echo '<script language="javascript">';
				echo 'alert("Fill form correctly...")';
				echo '</script>';
			}
		}
	}
?>
	

<body style="background: green;">
<form action="" method="POST">
<table style="width:500px; margin:100px auto; border-collapse:collapse;" border="1">
  <tr style="text-align:center; height:100px;">
    <td colspan="3" style="font-size:32px; font-family:Arial; color:#FFFFFF; background-color:#0066CC;">Register Form</td>
  </tr>
  <tr style="height:40px; background-color:#999999; text-align:center;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px;">Name: <input type="text" name="name"></td>
  </tr>
  <tr style="height:40px; background-color:#999999; text-align:center;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px;">Username: <input type="text" name="username"></td>
  </tr>
  <tr style="height:40px; background-color:#999999; text-align:center;" >
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px;">Password: <input type="password" name="password"></td>
  </tr>
   <tr style="height:40px; background-color:#999999; text-align:center;" >
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px;">Retype Password: <input type="password" name="repassword"></td>
  </tr>
  <tr style="height:40px; background-color:#999999; text-align:center;">
	<td style="width:20%; font-family:Arial; font-size:18px;"><a href="index.php"><input type="button" value="< Login Page"></a></td>
	<td style="width:20%; font-family:Arial; font-size:18px;"><input type="submit" value="Register"></td>
  </tr>

</table>
</form>




</body>
</html>
