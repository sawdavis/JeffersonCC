<?php
ob_start();
session_start();
$dbHost = "127.0.0.1";
$dbName = "ecomm";
$dbID = "ecomm24WR";
$dbPW = "()90eeC0oM-";
$conm = mysqli_connect($dbHost, $dbID, $dbPW, $dbName) or
        DIE("Not Able to Connect to the Database.");
?>
<!DOCTYPE html>
<html lang='en'>
<html>
    <head>
    <title>Eccomerce++</title>
        <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #fff;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 20px;
        }
        input[type="text"], select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            margin-right: 10px;
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
        .products {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
        }
        .product {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }
        .product img {
            width: 200px;
            height: 200px;
            margin-bottom: 10px;
            overflow: hidden;
        }
        .product h2 {
            font-size: 20px;
            margin-bottom: 10px;
            color: #333;
        }
        .product p {
            margin-bottom: 10px;
            color: #666;
        }
        .view-btn, .add-to-cart-btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
        }
        .view-btn:hover, .add-to-cart-btn:hover {
            background-color: #45a049;
        }
    </style>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body> 
        <div class ='hbar'>
        <p class= "hbar_button"><a link href= "home.php">ECOMMERCE++! </a></p>
        <p class= "hbar_button"><a link href= "Generate_Search_Val.php">Search </a></p>
        <p class= "hbar_button"><a link href = "cart_page.php">Cart </a></p>
        <p class= "hbar_button"><a link href= "settings.php">Login / Settings </a></p>
        </div>
        <link rel="stylesheet" href= "e-styles.css"/>
        <div>
        <h2>Featured Products</h2>
        </div>
        <div class="products">
            <?php include 'Generate_Match.php'; ?>
        </div>
    </body>

</html>

