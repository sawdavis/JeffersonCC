<?php
session_start();
include_once('connect.php');

if(!isset($_SESSION['logged_in']) || !$_SESSION['logged_in']) {
    header("Location: login.php");
    exit;
}

// Retrieve orders for the current user
$user_id = $_SESSION['user_id']; // Assuming you have user_id in the session
$sql = "SELECT * FROM orders WHERE user_id = $user_id";
$result = $conn->query($sql);

// Display order history
echo "<h1>Order History</h1>";
echo "<table border='1'>";
echo "<tr><th>Order ID</th><th>Product</th><th>Quantity</th><th>Status</th></tr>";
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        echo "<tr>";
        echo "<td>".$row['order_id']."</td>";
        echo "<td>".$row['product']."</td>";
        echo "<td>".$row['quantity']."</td>";
        echo "<td>".$row['status']."</td>"; // Assuming status is a column in your orders table
        echo "</tr>";
    }
} else {
    echo "<tr><td colspan='4'>No orders found.</td></tr>";
}
echo "</table>";

$conn->close();
?>
