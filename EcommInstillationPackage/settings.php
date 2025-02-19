<?php
session_start();
ob_start( );
if(!isset($_SESSION['logged_in'])) {
    header("Location: login.php");
    exit;
}

if(!isset($_SESSION["admin"]) || $_SESSION["admin"] == 0) {
    header("Location: User_Settings.php");
    exit;
}

if($_SESSION["admin"] == 1 ) {
    header("Location: Admin_Settings.php");
    exit;
}

ob_end_flush( );
?>