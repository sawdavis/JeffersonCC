<?php
session_start();
ob_start( );


if(!isset($_SESSION['logged_in'])) {
    header("Location: login.php");
    exit;
}

if(!isset($_SESSION["admin"])) {
    header("Location: home.php");
    exit;
}

if(!isset($_POST["userID"])) {
    header("Location: AdminSettings.php");
    exit;
}



ob_end_flush( );

//connect to the database
	$dbHost = "127.0.0.1";  //localhost
	$dbName = "ecomm";
	$dbID = "ecomm24WR";
	$dbPW = "()90eeC0oM-";
	$conn = mysqli_connect($dbHost, $dbID, $dbPW, $dbName) or
		DIE("Not able to connect to database.");

	$result = mysqli_query($conn, 
	   "SELECT * FROM `User` WHERE `UserID` = '".$_POST["userID"]."'");

	$row = mysqli_fetch_assoc($result);
?>
<!DOCTYPE html>
<HTML lang='en'>
	<head>
		
	</head>
		 <title>ECCOMERCE++</title>
	<body>

		<div class ='hbar'>
       		<p class= "hbar_button"><a link href= "home.php">ECOMMERCE++! </a></p>
        	<p class= "hbar_button"><a link href= "Generate_Search_Val.php">Search </a></p>
        	<p class= "hbar_button"><a link href= "settings.php">Login / Settings </a></p>

		
        	</div>
        	<link rel="stylesheet" href= "e-styles.css"/>
		
		<form method='POST' action='Update_Settings.php'>

		<?php echo "<p>First Name: <input type='text' id='FirstName' name='FirstName' value='".$row["FirstName"]."'> </p>\n";
		echo "<p>Last Name: <input type='text' id='LastName' name='LastName' value='".$row["LastName"]."'> </p>\n";
		echo "<p>Email: <input type='text' id='Email' name='Email' value='".$row["Email"]."'> </p>\n";
		echo "<p>Apartment: <input type='text' id='Apartment' name='Apartment' value='".$row["Apartment"]."'> </p>\n";
		echo "<p>City: <input type='text' id='City' name='City' value='".$row["City"]."'> </p>\n";
		echo "<p>State: <input type='text' id='State' name='State' value='".$row["State"]."'> </p>\n";
		echo "<p>Zip: <input type='text' id='Zip' name='Zip' value='".$row["Zip"]."'> </p>\n";
		echo "<p>Phone Number: <input type='text' id='PhoneNumber' name='PhoneNumber' value='".$row["PhoneNumber"]."'> </p>\n";
		echo "<p>Payment Method: <input type='text' id='PaymentMethod' name='PaymentMethod' value='".$row["PaymentMethod"]."'> </p>\n";
		echo "<p>Password: <input type='text' id='Password' name='Password' value='".$row["Password"]."'> </p>\n";
		echo "<p><input type='hidden' id='userID' name='userID' value='".$row["UserID"]."'> </p>\n";
		?>
		
		<div>
			<input type="submit">
		</div>
		
		
	</body
</HTML>