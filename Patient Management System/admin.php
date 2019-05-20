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
<div class="helper">
	Welcome, <?php echo $_SESSION['doc_name']; ?>...
</div>
 </main>
  



</body>

</html>
