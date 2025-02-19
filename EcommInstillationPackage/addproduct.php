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
$dbHost = "127.0.0.1";
$dbName = "ecomm";
$dbID = "ecomm24WR";
$dbPW = "()90eeC0oM-";
$conn = mysqli_connect($dbHost, $dbID, $dbPW, $dbName) or
        DIE("Not Able to Connect to the Database.");
?>
<!DOCTYPE html>
<html lang='en'>
<html>
    <head>
    <title>Eccomerce++</title>
        <style>
        .product {
            border: 1px solid #ccc;
            margin-bottom: 20px;
            padding: 10px;
            display: flex;
            align-items: center;
        }
        .product img {
            margin-right: 10px;
            max-width: 150px;
            max-height: 150px;
        }
        .product-details {
            flex: 1;
        }
    </style>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body> 
        <div class ='hbar'>
        <p class= "hbar_button"><a link href= "home.php">ECOMMERCE++! </a></p>
        <p class= "hbar_button"><a link href= "Generate_Search_Val.php">Search </a></p>
        <p class= "hbar_button"><a link href = "cart_page.html">Cart </a></p>
        <p class= "hbar_button"><a link href= "settings.php">Login / Settings </a></p>
        </div>
        <link rel="stylesheet" href= "e-styles.css"/>

        <form action="add.php" method="post">
            <section>
        <div>
            <label for="productName">Product Name:
            </label>
            <input type="text"
                   id="productName"
                   name="productName"
                   placeholder="Enter Product Name..." required>
        </div>
        <br>
        <div>
            <label for="prodDesc">Product Description:
            </label>
                <br>
            <textarea
                   style="font-size: 10pt; height: 5em; width:20em; vertical-align: top;"
                   id="prodDesc"
                   name="prodDesc"
                   placeholder="Type a product descsription..." required></textarea>
        </div>
        <br>
        <div>
            <form>
            <label for="myfile">Select a file:</label>
            <input type="file" id="myfile" name="myfile" required>
            </form>
        </div>
        <br>
        <div>
            <label for="price"> Price:
            </label>
                <br>
            <input type="number"
                   id="price"
                   name="price"
                   step="0.01"
                   min="0.99"
                   max="199.99"
                   placeholder="Price" required>
        </div>
            <br>  
            
        
        <div>
            <label for="quantity"> Quantity:
            </label>
                <br>
            <input type="number"
                   id="quantity"
                   name="quantity"
                   step="1"
                   min="0"
                   max="100"
                   placeholder="Quantity" required>
        </div>
        <br>
        <div>
            <label for="size"> Size:
            </label>
                <br>
            <input type="text"
                   id="text"
                   name="text"
                   placeholder="Enter a Size..." required>
        </div>
        <br>
        <div>
            <label for="color"> Color:
            </label>
                <br>
            <input type="text"
                   id="color"
                   name="color"
                   placeholder="Enter a Color..." required>
        </div>
        <br>
        <div>
            <label for="category"> Category:
            </label>
                <br>
            <select name="category"
                    id="category" required>
                    <option value="1">Shoes</option>
                    <option value="2">Shirts</option>
                    <option value="5">Pants</option>
                    <option value="6">Glasses</option>
            </select>
        </div>
        <br>
        <div>
            <button type="submit"
                    onclick="form_submit">
                    Upload
                    </button>
        </div>
        </form>
</html>
