<?php
session_start();
ob_start( );


if(!isset($_SESSION['logged_in'])) {
    header("Location: login.php");
    exit;
}

if(!isset($_SESSION["admin"]) || $_SESSION["admin"] != 1 ) {
    header("Location: home.php");
    exit;
}


if(!isset($_POST["Email"])) {
    header("Location: Admin_Settings.php");
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

	$result = mysqli_query($conn, "UPDATE `User` SET `Email` = '".$_POST["Email"]."',`Password` = '".$_POST["Password"]."' WHERE `User`.`UserID` = '".$_SESSION["userID"]."'");
	
	header("Location: Admin_Settings.php");
?>