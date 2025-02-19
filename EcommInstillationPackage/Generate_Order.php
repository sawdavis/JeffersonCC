<?php
session_start();
if ($_SESSION['logged_in'] !== 1) {
    header("Location: login.php");
    exit();
}
if ($_SERVER["REQUEST_METHOD"] == "POST") {
	$dbHost = "127.0.0.1";
	$dbName = "ecomm";
	$dbID = "ecomm24WR";
	$dbPW = "()90eeC0oM-";
	$conn = mysqli_connect($dbHost, $dbID, $dbPW, $dbName) or
		DIE("Not able to connect to database.");

    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    $userId = $_SESSION['userID'];
    $productId = $_POST['product_id'];
    $quantity = $_POST['quantity'];

    $sql = "INSERT INTO `Cart` (UserID, ProductID, Quan) VALUES ('$userId', '$productId', '$quantity')";

    if ($conn->query($sql) === TRUE) {
        header("Location: cart_page.php");
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $conn->close();
} else {
    header("Location: Item_Details.php");
    exit();
}
?>
