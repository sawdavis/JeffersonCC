<?php
date_default_timezone_set("America/New_York");
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

	//require('database_connection.php');
    //require('mysql-dump.php');
    $dumpSettings = array(
        'include-tables' => array('Cart', 'Category', 'Inventory', 'Order', 'OrderHistory', 'User'),
        'no-data' => false,            
        'add-drop-table' => false,      
        'single-transaction' => true,   
        'lock-tables' => false,        
        'add-locks' => true,            
        'extended-insert' => true      
    );

    $dump = new MySQLDump('ecomm','ecomm24WR','()90eeC0oM-','localhost', $dumpSettings);
    $dump->start('db_backups/forum_dump.sql');

header("Location: Admin_Settings.php");
    exit;
?>