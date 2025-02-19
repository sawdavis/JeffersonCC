<?php 
ob_start( );
session_start( );

if (!isset($_SESSION['logged_in']) || $_SESSION['logged_in'] !== 1) {
    header("Location: login.php");
    exit();
} 

//Connect to DB

$servername = "127.0.0.1";
$username = "ecomm24RO";
$password = "!@12Ec0mm+-";
$dbname = "ecomm";

$conn = new mysqli($servername, $username, $password, $dbname); 

if ($conn->connect_error) {
	DIE("Connection Failed: " . $conn->connect_error);	
}

//Update DB With new data

//$sql = "UPDATE 

ob_end_flush( );	

?>
<!DOCTYPE html>
<html lang = "en">

<head>
	<title> Product Manager </title>
	<meta charset = "utf-8">
	<link rel = "stylesheet" href = "e-styles.css">	
</head>

<body>
	<div class='hbar'>
		<p class= "hbar_button"><a link href= "home.php">ECOMMERCE++! </p></a>
		<p class= "hbar_button"><a link href= "Generate_Search_Val.php">Search </p></a>
		<p class= "hbar_button"><a link href= "cart_page.php">Cart </p></a>
		<p class= "hbar_button"><a link href= "settings.php">Login / Settings </p></a>
	</div>

<!--Form for editing existing products---------->
	<div style = 'float: left; clear: left;'>	
		
		<div class= "add_prod">

		<p class = "form_title"><a link href = "addproduct.php"> Add New Products Here </p></a><br>

		</div>

<!--Get ProductID to Search---------->
	
	<form action ='manage_products.php' method = "POST">
		<label for = 'SearchID'> Enter the ProductID of the product you'd like to manage: </label><br>
		<input type = 'text' name = 'SearchID' id = 'SearchID' value = '(#)'>
		<input type = 'submit' id = 'submit' value = 'Submit ID'>
	</form>

	
<!--Search for Match---------->

	<?php
	if($_SERVER['REQUEST_METHOD'] === 'POST') {
		$sql = "SELECT * from `Inventory` WHERE `ProductID` = '".$_POST["SearchID"]."'";
		$result = $conn->query($sql);


	
//Display Cart Products

	$row = mysqli_fetch_assoc($result);
	
//Display Match
	    $_SESSION["SearchID"] = $row["ProductID"];
	    //echo $_SESSION["SearchID"]."   ".$row["ProductID"];exit;
    	echo "<p>Current Name: ".$row["ProductName"]." </p>\n";
    	echo "<p>Current Price:  ".$row["ProductPrice"]."</p>\n";
    	echo "<p>Current Description:  ".$row["ProductDesc"]."</p>\n";
    	echo "<p>Current Inventory Quantity:  ".$row["ProductQuantities"]."</p>\n";
    	
    	echo"<p class = 'form_title'> Manage Existing Products: </p>";
echo"		<div class= 'manage_prod'>";

echo"		<form action = 'Update_product.php' method = 'POST' enctype = 'multipart/form-data'>";

echo"		<!--Change Photo--->	";

echo"		<label for = 'newImg'>Select a new Photo: </label>";
echo"		<input type = 'file' id = 'newImg' name = 'newImg'><br><br>";
echo"		<!---<input type = 'submit' value = 'Select Photo'><br><br>--->		";

echo"		<!--Change Name--->";

echo"		<label for = 'newName'> Enter New Name: </label><br>";
echo"		<input type = 'text' id = 'newName' name = 'newName' value = '".$row["ProductName"]."'><br><br>";

echo"		<!--Change Price--->	";

echo"		<label for = 'newPrice'> Enter New Price: ($##.##)</label><br>";
echo"		<input type = 'number' id = 'newPrice' name = 'newPrice' min = '0.00' step = '0.01' value = '".$row["ProductPrice"]."'><br><br>";

echo"		<!--Change Description--->";

echo"		<label for = 'newDesc'> Enter the New Item Description: </label><br>";
echo"		<input type = 'text' id = 'newDesc' name = 'newDesc' value = '".$row["ProductDesc"]."'><br><br>";

echo"		<!--Change Stocked Quantity--->";

echo"		<label for = 'newQuan'> Enter New Quantity: </label><br>";
echo"		<input type = 'number' id = 'newQuan' name = 'newQuan' min = '0' step='1' value = '".$row["ProductQuantities"]."'><br><br>";

		

echo"		<!--Submit Change form--->";

echo"		<label for = 'newProdSubmission'> If all data is correct, press 'Finish' to submit the new data, or 'Reset' to reset all entries. </label><br>";
echo"		<input type = 'submit' value = 'Finish'><br>	";
echo"		<input type = 'reset'>	";

echo"		</form>";


	}	
	?>

		</div>
			
	</div>
	

</body>

<footer>

</footer>

</html>