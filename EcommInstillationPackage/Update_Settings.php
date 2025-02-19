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
    header("Location: User_Settings.php");
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

	$result = mysqli_query($conn, "UPDATE `User` SET `FirstName` = '".$_POST["FirstName"]."',`LastName` = '".$_POST["LastName"]."',`Email` = '".$_POST["Email"]."',`Apartment` = '".$_POST["Apartment"]."', `City` = '".$_POST["City"]."',`State` = '".$_POST["State"]."', `Zip` = '".$_POST["Zip"]."', `PhoneNumber` = '".$_POST["PhoneNumber"]."', `PaymentMethod` = '".$_POST["PaymentMethod"]."', `Password` = '".$_POST["Password"]."' WHERE `User`.`UserID` = '".$_POST["userID"]."'");

?>

<!DOCTYPE html>
<HTML lang='en'>
	<head>
		
	</head>
	
	<body>

		<div class ='hbar'>
       		<p class= "hbar_button"><a link href= "home.php">ECOMMERCE++! </a></p>
        	<p class= "hbar_button"><a link href= "Generate_Search_Val.php">Search </a></p>
        	<p class= "hbar_button"><a link href= "settings.php">Login / Settings </a></p>

        	</div>
        	<link rel="stylesheet" href= "e-styles.css"/>
		
		<?php echo "<p>First Name: ".$_POST["FirstName"]."</p> \n";
		echo "<p>Last Name: ".$_POST["LastName"]."</p> \n";
		echo "<p>Email: ".$_POST["Email"]."</p> \n";
		echo "<p>Apartment: ".$_POST["Apartment"]."</p> \n";
		echo "<p>City: ".$_POST["City"]."</p> \n";
		echo "<p>State: ".$_POST["State"]."</p> \n";
		echo "<p>Zip: ".$_POST["Zip"]."</p> \n";
		echo "<p>Phone Number: ".$_POST["PhoneNumber"]."</p> \n";
		echo "<p>Payment Method: ".$_POST["PaymentMethod"]."</p> \n";
		echo "<p>Password: ".$_POST["Password"]." </p> \n<p>Changes Performed Successfully</p>";
		?>
		


	</body
</HTML>