<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Item Details</title>
    <link rel="stylesheet" href="e-styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .product {
            display: flex;
            flex-direction: column;
            align-items: center;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: 0 auto;
            margin-top: 20px;
        }

        .product img {
            width: 200px;
            height: 200px;
            margin-bottom: 10px;
            overflow: hidden;
        }

        .product-details {
            text-align: center;
        }

        .product-details h2 {
            margin-bottom: 10px;
        }

        .product-details p {
            margin-bottom: 10px;
            color: #666;
        }

        form {
            margin-top: 20px;
            text-align: center;
        }

        select[name='size'], input[type="number"] {
            margin: 10px auto;
            display: block;
            padding: 8px;
            font-size: 14px;
            border-radius: 5px;
            border: 1px solid #ccc;
            width: 100px;
            
        }

        button[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        button[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="hbar">
        <p class="hbar_button"><a href="home.php">ECOMMERCE++!</a></p>
        <p class="hbar_button"><a href="Generate_Search_Val.php">Search</a></p>
        <p class="hbar_button"><a href="cart_page.php">Cart Page</a></p>
        <p class="hbar_button"><a href="login.php">Login / Settings</a></p>
    </div>

    <?php
    if (isset($_GET['ProductName'])) {
        $servername = "127.0.0.1";
        $username = "ecomm24RO";
        $password = "!@12Ec0mm+-";
        $dbname = "ecomm";

        $conn = new mysqli($servername, $username, $password, $dbname);

        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        $productName = $_GET['ProductName'];

        $sql = "SELECT ProductID, Color, ProductDesc, ProductName, ProductImages, ProductPrice, Size, ProductQuantities
                FROM Inventory 
                WHERE ProductName = '$productName'";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            $firstRow = $result->fetch_assoc();
            $productID = $firstRow["ProductID"];
            $color = $firstRow["Color"];
            $desc = $firstRow["ProductDesc"];
            $name = $firstRow["ProductName"];
            $image = $firstRow["ProductImages"];
            $price = $firstRow["ProductPrice"];

            echo "<div class='product'>";
            echo "<div class='product-details'>";
            if (!empty($image)) {
                echo "<img src='data:image/jpeg;base64," . base64_encode($image) . "' alt='Product Image'>";
            }
            echo "<h2>$name</h2>";
            echo "<p>$desc</p>";
            echo "<p>Price: $$price</p>";
            echo "<p>Color: $color</p>";
            echo "<p><form action='Generate_Order.php' method='POST'><p>";
            echo "<select name='product_id'>";
            do {
                $size = $firstRow["Size"];
                $productID = $firstRow["ProductID"];
                $quantity = $firstRow["ProductQuantities"];
                if ($quantity > 0) {
                    echo "<option value='$productID'>$size (Stock: $quantity)</option>";
                } else {
                    echo "<option value='' style='color: red;' disabled>$size (Out of Stock)</option>";
                }
            } while ($firstRow = $result->fetch_assoc());
            if ($quantity > 0) {
                echo "</select>";
                echo "<input type='number' name='quantity' min='1' placeholder='Quantity' required>";
                echo "<button type='submit'>Add to Cart</button>";
            }
            echo "</form>";
            echo "</div>";
            echo "</div>";
        } else {
            echo "<p>Item not found.</p>";
        }
        $conn->close();
    } else {
        echo "<p>No product name provided.</p>";
    }
    ?>
</body>
</html>
