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
        <h3>Your new item has been added!!!</h3>
        <div>
        <p><a link href="home.php">Click to go back Home</a></p>
        </div>
        <div>
        <p><a link href="settings.php">Settings</a></p>
        </div>
    </body>
</html>