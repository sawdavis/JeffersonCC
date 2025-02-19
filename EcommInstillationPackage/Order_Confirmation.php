<?php
ob_start();
session_start();
if(!isset($_SESSION['logged_in'])){
header("Location: home.php");
exit();
}

$dbHost = "127.0.0.1";
$dbName = "ecomm";
$dbID = "ecomm24WR";
$dbPW = "()90eeC0oM-";
$conn = mysqli_connect($dbHost, $dbID, $dbPW, $dbName) or
        DIE("Not Able to Connect to the Database.");
        
$sql = "select `Cart`.`ProductID`, `Cart`.`Quan`,`Inventory`.`ProductQuantities` from `Cart` inner join `Inventory` ON`Cart`.`ProductID`=`Inventory`.`ProductID` where `Cart`.`UserID`='".$_SESSION["userID"]."'";

$result = $conn->query($sql);

$cnt = 0;
$ProductID = [ ];
$Quan = [ ];
while($row = mysqli_fetch_assoc($result)) {
    $ProductID[$cnt] = $row["ProductID"];
    if($row["Quan"] < $row["ProductQuantities"])
    	$Quan[$cnt] = $row["Quan"];
    else
    	$Quan[$cnt] = $row["ProductQuantities"];
    
    /*echo "<p>".$row["ProductID"]."----".$row["Quan"]."----".$row["ProductQuantities"]."----".$cnt."</p>";
    echo "<p>".$ProductID[$cnt]."    ".$Quan[$cnt]."    ".$cnt."</p>";*/
    
    $cnt++;
}

$sql = "select `Order`.`OrderID` from `Order` order by `Order`.`OrderID` Desc Limit 1";

$result = $conn->query($sql);

$row = mysqli_fetch_assoc($result);

$orderID = $row["OrderID"] + 1;

$vals = "";
for($i = 0; $i < count($Quan); $i++) 
    $vals = $vals."('".$orderID."', '".$_SESSION["userID"]."', '".$ProductID[$i]."', '".$Quan[$i]."'),";
    
    $vals = substr($vals, 0, strlen($vals) - 1);
    
    $sql = "INSERT INTO `Order`(`OrderID`, `UserID`, `ProductID`, `Quan`) VALUES ".$vals;
    //echo "<p>".$sql."</p>";

$result = $conn->query($sql);

$sql = "DELETE from `Cart` WHERE `UserID` = '".$_SESSION["userID"]."'";

$result = $conn->query($sql);

//Update Inventory Quantities



$sql = "SELECT `Inventory`.`ProductID`, `Order`.`Quan` , `Inventory`.`ProductQuantities` from `Order` inner JOIN `Inventory` on
 `Order`.`ProductID` = `Inventory`.`ProductID` WHERE `Order`.`OrderID` = '".$orderID."'";

$result = $conn->query($sql);

while($row = mysqli_fetch_assoc($result)) {
	$newQuan = $row["ProductQuantities"] - $row["Quan"];
	$sql2 = "UPDATE `Inventory` SET `Inventory`.`ProductQuantities` = '".$newQuan."' WHERE `Inventory`.`ProductID` = '".$row["ProductID"]."'";
	$result2 = $conn->query($sql2);
}



?>
<!DOCTYPE html>
<html lang='en'>
<html>
    <head>
        <title>ECCOMERCE++</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h3>Your order has been confirmed!</h3>
        <div>
        <p><a link href="home.php">Click to go back Home</a></p>
        </div>
        <div>
        <p><a link href="settings.php">Settings</a></p>
        </div>
    </body>
</html>
<?php

ob_end_flush();
?>