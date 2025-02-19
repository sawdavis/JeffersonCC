<?php
ob_start();
session_start();
$dbHost = "127.0.0.1";
$dbName = "ecomm";
$dbID = "ecomm24WR";
$dbPW = "()90eeC0oM-";
$conm = mysqli_connect($dbHost, $dbID, $dbPW, $dbName) or DIE("Not Able to Connect to the Database.");

// Function to sanitize input data
function sanitize($data) {
    global $conm;
    return mysqli_real_escape_string($conm, trim($data));
}

// Function to hash passwords
function hashPassword($password) {
    return password_hash($password, PASSWORD_DEFAULT);
}

// Function to insert user data into the database
function createUser($userId, $firstName, $lastName, $city, $state, $zip, $apartment, $phoneNumber, $email, $paymentMethod, $admin, $password) {
    global $conm;
    $hashedPassword = hashPassword($password);
    $query = "INSERT INTO users (userId, firstName, lastName, city, state, zip, apartment, phoneNumber, email, paymentMethod, admin, password) VALUES ('$userId', '$firstName', '$lastName', '$city', '$state', '$zip', '$apartment', '$phoneNumber', '$email', '$paymentMethod', '$admin', '$hashedPassword')";
    return mysqli_query($conm, $query);
}

// Check if the form is submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Sanitize input data
    $userId = sanitize($_POST["UserID"]);
    $firstName = sanitize($_POST["FirstName"]);
    $lastName = sanitize($_POST["LastName"]);
    $city = sanitize($_POST["City"]);
    $state = sanitize($_POST["State"]);
    $zip = sanitize($_POST["Zip"]);
    $apartment = sanitize($_POST["Apartment"]);
    $phoneNumber = sanitize($_POST["PhoneNumber"]);
    $email = sanitize($_POST["Email"]);
    $paymentMethod = sanitize($_POST["PaymentMethod"]);
    $admin = isset($_POST["Admin"]) ? 1 : 0; // Assuming Admin checkbox is present in the form
    $password = sanitize($_POST["Password"]);

    // Create user
    if (createUser($userId, $firstName, $lastName, $city, $state, $zip, $apartment, $phoneNumber, $email, $paymentMethod, $admin, $password)) {
        // Redirect to success page or perform any other action
        header("Location: success.php");
        exit;
    } else {
        // Handle error
        $error = "Error creating user. Please try again.";
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account</title>
    </style>
</head>
<body>
    <h1>Create Account</h1>
    
    <?php if(isset($error)): ?>
    <p><?php echo $error; ?></p>
    <?php endif; ?>

    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
        <label for="UserID">UserID:</label>
        <input type="text" name="UserID" required><br>

        <label for="FirstName">First Name:</label>
        <input type="text" name="FirstName" required><br>

        <label for="LastName">Last Name:</label>
        <input type="text" name="LastName" required><br>

        <label for="City">City:</label>
        <input type="text" name="City" required><br>

        <label for="State">State:</label>
        <input type="text" name="State" required><br>

        <label for="Zip">Zip:</label>
        <input type="text" name="Zip" required><br>

        <label for="Apartment">Apartment:</label>
        <input type="text" name="Apartment"><br>

        <label for="PhoneNumber">Phone Number:</label>
        <input type="text" name="PhoneNumber" required><br>

        <label for="Email">Email:</label>
        <input type="text" name="Email" required><br>

        <label for="PaymentMethod">Payment Method:</label>
        <input type="text" name="PaymentMethod" required><br>

        <label for="Password">Password:</label>
        <input type="password" name="Password" required><br>

        <button type="submit">Create Account</button>
    </form>
</body>
</html>
