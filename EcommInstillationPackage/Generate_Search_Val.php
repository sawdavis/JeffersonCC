<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <title>Product Page</title>
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
            align-self: center;
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
</head>
<body>
    <div class='hbar'>
            <p class= "hbar_button"><a link href= "home.php">ECOMMERCE++! </a></p>
            <p class= "hbar_button"><a link href= "Generate_Search_Val.php">Search </a></p>
            <p class= "hbar_button"><a link href= "cart_page.php">Cart Page </a></p>
            <p class= "hbar_button"><a link href= "login.php">Login / Settings </a></p>
         </div> 
    <title> Selected_Item </title>
    <meta charset = "utf-8" />
    <link rel="stylesheet" href= "e-styles.css" />
    <div>
        <h1>Product Page</h1>
        <form method="GET">
            <input type="text" name="search" placeholder="Search by product name">
            <select name="sort_by">
                <option value="">Sort by...</option>
                <option value="product_name">Product Name</option>
                <option value="price_asc">Price Low to High</option>
                <option value="price_desc">Price High to Low</option>
            </select>
            <button type="submit">Search</button>
        </form>
        <div class="products">
            <?php include 'Generate_Match.php'; ?>
        </div>
    </div>
</body>
</html>
