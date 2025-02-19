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

// Retrieve all orders
$sql = "SELECT * FROM orders";
$result = $conn->query($sql);

// Display all orders
echo "<h1>All Orders</h1>";
echo "<table border='1'>";
echo "<tr><th>Order ID</th><th>User ID</th><th>Product</th><th>Quantity</th><th>Status</th></tr>";
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        echo "<tr>";
        echo "<td>".$row['order_id']."</td>";
        echo "<td>".$row['user_id']."</td>"; // Assuming user_id is a column in your orders table
        echo "<td>".$row['product']."</td>";
        echo "<td>".$row['quantity']."</td>";
        echo "<td>".$row['status']."</td>"; // Assuming status is a column in your orders table
        echo "</tr>";
    }
} else {
    echo "<tr><td colspan='5'>No orders found.</td></tr>";
}
echo "</table>";

$conn->close();
?>
