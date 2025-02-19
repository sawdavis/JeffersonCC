<?php 
ob_start( );
session_start( );

if (!isset($_SESSION['logged_in']) || $_SESSION['logged_in'] !== 1) {
    header("Location: login.php");
    exit();
} 

if(!isset($_SESSION["SearchID"]) || strlen($_SESSION["SearchID"]) == 0) {
    header("Location: manage_products.php");
    exit( );
}
$SearchID = $_SESSION["SearchID"];
//echo $SearchID."  ".$_SESSION["SearchID"];exit;
unset($_SESSION["SearchID"]);
$newName = $_POST["newName"];
//$newImg = file_get_contents($_FILES['newImg']['tmp_name']);
$newQuan=$_POST["newQuan"];
$newDesc=$_POST["newDesc"];
$newPrice=$_POST["newPrice"];

//Connect to DB

$dbHost = "127.0.0.1";
$dbName = "ecomm";
$dbID = "ecomm24WR";
$dbPW = "()90eeC0oM-";
$conn = mysqli_connect($dbHost, $dbID, $dbPW, $dbName) or
        DIE("Not Able to Connect to the Database.");
        
if ($conn->connect_error) {
	DIE("Connection Failed: " . $conn->connect_error);	
}


//Update DB With new data

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $uploadDir = 'temp/';  // Specify the directory to save uploaded files

    // Check for errors in the file upload
    if ($_FILES['newImg']['error'] === 0) {
        $tempFile = $_FILES['newImg']['tmp_name'];
        $targetFile = $uploadDir . basename($_FILES['newImg']['name']);
        move_uploaded_file($tempFile, $targetFile);
        //echo "/".$targetFile;exit;
        chmod($targetFile, 0777);
/*
        // Move the uploaded file to the specified directory
        if (move_uploaded_file($tempFile, $targetFile)) {
            echo "File uploaded successfully!";
        } else {
            echo "Error moving file to destination.";
        }*/
    }

$sql = "UPDATE `Inventory` SET `ProductName`='".$newName."',`ProductImages`=LOAD_FILE('/".$targetFile."'),`ProductQuantities`='".$newQuan."',`ProductDesc`='".$newDesc."',`ProductPrice`='".$newPrice."' WHERE `ProductID`='".$SearchID."'";
/*$sql = "INSERT INTO `Inventory`(`ProductName`, `ProductImages`, `ProductQuantities`, `ProductDesc`, `ProductPrice`, ) VALUES (".$newName."',".LOAD_FILE($targetFile).",'".$newQuan."','".$newDesc."','".$newPrice."')";*/


$result = mysqli_query($conn, $sql);
}
ob_end_flush();

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
        <h3>The selected product has been updated. </h3>
	<div>
<!---Display Updated Data--->
		<p>The new info you entered is as follows: </p>
		<ul>
			<li>Name: <?php echo $newName; ?> </li>
			<li>Stock: <?php echo $newQuan; ?> </li>
			<?php
			   // if(isset($newImg))
			   //     echo "<li>New Inventory Image: ".$newImg." </li>\n";
			?>
			<li>New Description: <?php echo $newDesc; ?> </li><br>
			<li>New Price: <?php echo $newPrice; ?> </li>
		</ul>
	</div>
        <div>
	        <p><a link href="home.php">Click to go back Home</a></p>
        </div>
        <div>
        	<p><a link href="settings.php">Settings</a></p>
        </div>
    </body>
</html>