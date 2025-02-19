<?php
session_start();
include_once('connect.php');

if(!isset($_SESSION['logged_in']) || !$_SESSION['logged_in']) {
    header("Location: login.php");
    exit;
}

if (!isset($_SESSION['admin']) || !$_SESSION['admin']) {
    header("Location: home.php"); // Redirect to home for non-admin users
    exit;
}

// Check if order_id and status are set in the request
if(isset($_GET['order_id']) && isset($_GET['status'])) {
    $order_id = $_GET['order_id'];
    $status = $_GET['status'];

    // Update order status
    $sql = "UPDATE orders SET status = '$status' WHERE order_id = $order_id";
    if ($conn->query($sql) === TRUE) {
        echo "Order status updated successfully";
    } else {
        echo "Error updating order status: " . $conn->error;
    }
} else {
    echo "Missing order_id or status";
}

$conn->close();
?>
