<?php
ob_start();
session_start();
if(!isset($_SESSION['logged_in'])){
    header("Location: home.php");
    exit();
}
if($_SESSION["admin"] == 0){
    header("Location: home.php");
    exit();
}
$dbHost = "127.0.0.1";
$dbName = "ecomm";
$dbID = "ecomm24WR";
$dbPW = "()90eeC0oM-";
$conn = mysqli_connect($dbHost, $dbID, $dbPW, $dbName) or
        DIE("Not Able to Connect to the Database.");

$sql = "INSERT INTO `Inventory` (`CategoryID`, `ProductName`, `ProductQuantities`, `ProductDesc`, `ProductPrice`, `Size`, `Color`)
        VALUES ('".$_POST["category"]."' ,'".$_POST["productName"]."', '".$_POST["quantity"]."', '".$_POST["prodDesc"]."', '".$_POST["price"]."', '".$_POST["size"]."', '".$_POST["color"]."')";
$conn->query($sql);
header("home.php");
ob_end_flush();
if($conn->query($sql) === TRUE){
    header("Location: added.php");
} else{
    echo "Error: " . $sql . "<br>" . $conn->error;
}


