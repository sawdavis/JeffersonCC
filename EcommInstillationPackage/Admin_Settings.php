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

ob_end_flush( );

//connect to the database
	$dbHost = "127.0.0.1";  //localhost
	$dbName = "ecomm";
	$dbID = "ecomm24WR";
	$dbPW = "()90eeC0oM-";
	$conn = mysqli_connect($dbHost, $dbID, $dbPW, $dbName) or
		DIE("Not able to connect to database.");

	$result = mysqli_query($conn, 
	   "SELECT `Email`, `Password`, `UserID`, `FirstName`, `LastName` FROM `User` WHERE `UserID` = '".$_SESSION["userID"]."'");

	$row = mysqli_fetch_assoc($result);
	
?>
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

		<form method='POST' action='Admin_Update.php'>

		<?php
		echo "<p>Email: <input type='text' id='Email' name='Email' value='".$row["Email"]."'> </p>\n";
		echo "<p>Password: <input type='text' id='Password' name='Password' value='".$row["Password"]."'> </p>\n";
		?>
		
		<div>
			<input type="submit">
		</div>
		</form>
		<div>
        	<p><a link href="Order_History.php">Order History</a></p>
        	</div>

		<!--Manage User Drop Down-->
		<form method='POST' action='Manage_User.php'>
		
		<label for="Users">Select A User:</label>

		<select name="userID" id="userID">
		<?php while($row=mysqli_fetch_assoc($result))
 		 		echo "<option value='".$row["UserID"]."'>".$row["UserID"]." ".$row["FirstName"]." ".$row["LastName"]."</option>\n";
  		 ?>
		</select>
		<div>
			<input type="submit">
		</div>
		</form>

		<div>
        	<p><a link href="manage_products.php">Manage Products</a></p>
        	</div>

		<div>
        	<p><a link href="System_Backup.php">Perform a System Backup</a></p>
        	</div>


		<div>
        	<p><a link href="addproduct.php">Add Product(s)</a></p>
        	</div>
	</body
</HTML>
