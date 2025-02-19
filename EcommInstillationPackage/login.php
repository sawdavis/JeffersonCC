<?php
    session_start( );   		
?>
<!DOCTYPE html>
<html lang='en'>
    <head>
        <title>Login</title>
        <meta charset='utf-8'>
    </head>
    <body>
        <div class='hbar'>
        <p class= "hbar_button"><a link href= "home.php">ECOMMERCE++! </a></p>
        <p class= "hbar_button"><a link href= "Generate_Search_Val.php">Search </a></p>
    	<p class= "hbar_button"><a link href= "cart_page.php">Cart Page </a></p>
    	<p class= "hbar_button"><a link href= "login.php">Login / Settings </a></p>
    	</div> 
    	<meta charset = "utf-8" />
   	<link rel="stylesheet" href= "e-styles.css" />
	
    <h1>Please Log In</h1>
    <form action="login_process.php" method="post">
        <label for="userID">Username:</label><br>
        <input type="text" id="userID" name="userID"><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Login">
	</form>
	<?php
	if(isset($_SESSION["inpErr"]))
	    echo "<p>User ID and Password must NOT be blank.</p>\n";
	if(isset($_SESSION["credErr"]))
	    echo "<p>User ID and Password combination do not match.</p>\n";
	?>
	<div>
        <p><a link href="Create_Account.php">Create Account</a></p>
        </div>
    </body>
</html>
