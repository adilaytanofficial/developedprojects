<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login Form</title>
</head>

<?php 
	session_start();
	if($_POST){
		
		$name=$_POST["username"];
		$pass=$_POST["password"];
		if($name != "" && $pass != ""){
			 $pass=md5($pass);
			 echo $pass;
			 $DBServer= mysqli_connect('localhost', 'root', '') or die('Connection failed!');
			 mysqli_select_db($DBServer,'doctors') or die('Database Connect Error!');
			 $query="SELECT * FROM doctors WHERE uname='" . $name . "' AND pass='" . $pass . "'";
			 $data= mysqli_query($DBServer, $query) or die('Read ERROR');
			 $num_records= mysqli_num_rows($data);
			 if ($num_records > 0){
				$_SESSION['user'] = $name;
				$rec= mysqli_fetch_array($data);
				$_SESSION['doc_name'] = $rec['doctorname'];
				$_SESSION['doc_id'] = $rec['doctorid'];
				header("location: admin.php");
			 }
			 else
			 {
				echo '$pass';
			 }
		}
	}
	elseif(isset($_SESSION['doc_name']) && isset($_SESSION['user']) && isset($_SESSION['doc_id'])) {
		header("location: admin.php");
	}
	
?>

<body style="background: green;">
<form action="" method="POST">
<table style="width:500px; margin:100px auto; border-collapse:collapse;" border="1">
  <tr style="text-align:center; height:100px;">
    <td colspan="3" style="font-size:32px; font-family:Arial; color:#FFFFFF; background-color:#0066CC;">Patient Login Form</td>
  </tr>
  <tr style="height:40px; background-color:#999999; text-align:center;">
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px;">Username: <input type="text" name="username"></td>
  </tr>
  <tr style="height:40px; background-color:#999999; text-align:center;" >
    <td colspan="2" style="width:20%; font-family:Arial; font-size:18px;">Password: <input type="password" name="password"></td>
  </tr>
  <tr style="height:40px; background-color:#999999; text-align:center;">
	<td style="width:20%; font-family:Arial; font-size:18px;"><a href="register.php"><input type="button" value="Register"></a></td>
	<td style="width:20%; font-family:Arial; font-size:18px;"><input type="submit" value="Login"></td>
  </tr>

</table>
</form>




</body>
</html>
