<?php
	ob_start( );
	session_start( );
			
	$inpErr = 0;
	if(!isset($_POST["userID"]) || strlen($_POST["userID"]) == 0)
	    $inpErr = 1;
	if(!isset($_POST["password"]) || strlen($_POST["password"]) == 0)
	    $inpErr = 1;

	unset($_SESSION["inpErr"]);
	if($inpErr == 1) {
	    $_SESSION["inpErr"] = 1;
	    header("Location: login.php");
	    exit( );
	}

	//connect to the database
	$dbHost = "127.0.0.1";  //localhost
	$dbName = "ecomm";
	$dbID = "ecomm24WR";
	$dbPW = "()90eeC0oM-";
	$conn = mysqli_connect($dbHost, $dbID, $dbPW, $dbName) or
		DIE("Not able to connect to database.");

	//retrieve the userid and password
	unset($_SESSION["credErr"]);
	$userID = filter_var($_POST["userID"], FILTER_SANITIZE_STRING);
	$result = mysqli_query($conn, 
	   "SELECT `Password`, `UserID`, `Admin` FROM `User` WHERE `UserID` = '".$userID."'");
	if(!$result) {  //userID did not match - not found.
	    $_SESSION["credErr"] = 1;
	    header("Location: login.php");
	    exit( );
	}
	$row = mysqli_fetch_assoc($result);
	//echo $row["Password"]."-".$_POST["password"];exit( );

	//if they DO NOT match send them back
	if($row["Password"] != $_POST["password"]) {
	    $_SESSION["credErr"] = 1;
	    header("Location: login.php");
	    exit( );
	}
	else{
		$_SESSION["logged_in"] = 1;
		$_SESSION["admin"] = $row["Admin"];
		$_SESSION["userID"] = $row["UserID"];
		header("Location: home.php");
		exit( );

	}
	ob_end_flush( );
?>