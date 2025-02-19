<?php
if ($_SERVER["SCRIPT_FILENAME"] == __FILE__) {
    header("Location: Generate_Search_Val.php");
    exit();
}

$servername = "127.0.0.1";
$username = "ecomm24RO";
$password = "!@12Ec0mm+-";
$dbname = "ecomm";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT ProductID, ProductName, MAX(CategoryID) AS CategoryID, MAX(ProductQuantities) AS ProductQuantities, MAX(ProductImages) AS ProductImages, MAX(ProductDesc) AS ProductDesc, MAX(ProductPrice) AS ProductPrice, MAX(Color) AS Color FROM Inventory GROUP BY ProductName";
if (isset($_GET['search']) && !empty($_GET['search'])) {
    $search = strtolower($_GET['search']);
    $sql .= " HAVING LOWER(ProductName) LIKE '%$search%'";
}

if (isset($_GET['sort_by'])) {
    $sort_by = $_GET['sort_by'];
    switch ($sort_by) {
        case 'product_name':
            $sql .= " ORDER BY ProductName ASC";
            break;
        case 'price_asc':
            $sql .= " ORDER BY ProductPrice ASC";
            break;
        case 'price_desc':
            $sql .= " ORDER BY ProductPrice DESC";
            break;
        default:
            break;
    }
}

$result = $conn->query($sql);

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        echo "<div class='product'>";
        if (!empty($row["ProductImages"])) {
            echo "<div class='product-image'>";
            echo "<img src='data:image/jpeg;base64," . base64_encode($row["ProductImages"]) . "' alt='Product Image'>";
            echo "</div>";
        }
        echo "<div class='product-details'>";
        echo "<h2>" . $row["ProductName"] . "</h2>";
        echo "<p>" . $row["ProductDesc"] . "</p>";
        echo "<p>Price: $" . $row["ProductPrice"] . "</p>";
        echo "<p>Color: " . $row["Color"] . "</p>";
        echo "<div style='text-align: center;'>";
        echo "<a href='Item_Details.php?ProductName=" . urlencode($row["ProductName"]) . "' class='view-btn'>View Item</a>";
        echo "</div>";
        echo "</div>";
        echo "</div>";
    }
} else {
    echo "No products found.";
}
$conn->close();
?>
