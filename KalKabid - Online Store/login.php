<?php
// Start session
session_start();

// Include header
include('header.php');

// Database connection
include('config.php');

// Initialize error message
$errorMessage = "";

// Check if form is submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Get username and password from form
    $username = $_POST['username'];
    $password = $_POST['password'];

    // Prepare and execute query to check user credentials
    $sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
    $stmt = $con->prepare($sql);
    $stmt->bind_param("ss", $username, $password);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows == 1) {
        // Fetch user data
        $user = $result->fetch_assoc();

        // Set session variables
        $_SESSION['username'] = $user['username'];
        $_SESSION['privilege'] = $user['privilege']; // Store privilege level in session

        if ($user['privilege'] == 1) {
            header("Location: products.php");
            exit();
        } else {
            header("Location: index.php");
            exit();
        }

        // Redirect to products page
        header("Location: products.php");
        exit();
    } else {
        $errorMessage = "Invalid username or password.";
    }

    // Close statement
    $stmt->close();

    // Close database connection
    $con->close();
}
?>

<!-- Main Content -->
<div class="container mt-5">
    <h2 class="text-center mb-4">Login</h2>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <!-- Display error message if any -->
            <?php if (!empty($errorMessage)): ?>
                <div class="alert alert-danger"><?php echo $errorMessage; ?></div>
            <?php endif; ?>

            <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
        </div>
    </div>
</div>

<?php
// Include footer
include('footer.php');
?>
