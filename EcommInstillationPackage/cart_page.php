<?php

ob_start( );
session_start();

if (!isset($_SESSION['logged_in']) || $_SESSION['logged_in'] !== 1) {
    header("Location: login.php");
    exit();
} 

ob_end_flush( );	

?>

<!DOCTYPE html>
<html lang ="en">

	<head>
		<title> Your Cart: </title>
		<meta charset = "utf-8">
		<link rel= "stylesheet" href= "e-styles.css">	
		 
	</head> 

	<body>


<!----Basic buttons for hotbar----->
	<div class='hbar'>
			<p class= "hbar_button"><a link href= "home.php">ECOMMERCE++! </a></p>
			<p class= "hbar_button"><a link href= "Generate_Search_Val.php">Search </a></p>
			<p class= "hbar_button"><a link href= "User_Settings.php">Login / Settings </a></p>
		 </div>

<!----Products in list + scrollbar--->
	<div class = "main">

		<table>
			<tr>
				<th>Item Photo </th>
				<th>Item Name </th>
				<th>Item $ </th>
				<th># of Item(s) </th>
			</tr>	

		<!- Insert DB connection, and (for loop) for pulling items from DB--->
		<?php
		//Connect to DB

		$servername = "127.0.0.1";
		$username = "ecomm24RO";
		$password = "!@12Ec0mm+-";
		$dbname = "ecomm";

		$conn = new mysqli($servername, $username, $password, $dbname); 

		if ($conn->connect_error) {
		DIE("Connection Failed: " . $conn->connect_error);	
		}

//Read In Products

	$sql = "SELECT `Cart`.`Quan`, `Inventory`.`ProductID`, `Cart`.`ProductID`, `Inventory`.`ProductName`, `Inventory`.`ProductImages`, `Inventory`.`ProductPrice` from `Inventory` INNER JOIN `Cart` ON `Cart`.`ProductID` = `Inventory`.`ProductID` WHERE `Cart`.`UserID` = '".$_SESSION["userID"]."'";

	$result = $conn->query($sql);


	
//Display Cart Products
//Calculate current subtotal, "$_SESSION[userTotal]"

	$userTotal = 0;
	while ($row = mysqli_fetch_assoc($result) ) {

		echo"<tr>\n";
			echo"<td><img src='data:image/jpeg;base64," . base64_encode($row["ProductImages"]) . "' alt='Product Image'></td>\n";
			echo"<td>".$row['ProductName']."</td>\n";
			echo"<td>".$row['ProductPrice']."</td>\n";
			echo"<td>".$row['Quan']."</td>\n";
		echo"</tr>\n";
		
		
		$userTotal = $userTotal + $row['ProductPrice'] * $row['Quan'];
 		
}
		?>	
		</table>


	</div>
	<?php
	echo "<p style = 'font-weight:900;float:left;clear:left;'>Current Subtotal:  $".number_format($userTotal, 2, ".", ",")."</p>\n";
	?>
	
	<!----Footer for "Checkout"----->
	<footer class = "check">
		<p><a link href="Order_Confirmation.php">Checkout</a></p>
	</footer>
	</body>


</html>