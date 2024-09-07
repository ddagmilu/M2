<?php
// Include header
include('header.php');

// Start session
session_start();

// Database connection
include('config.php');

// Initialize error messages
$errorMessage = "";
$successMessage = "";

// Check if form is submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Get username, email, and password from form
    $username = $_POST['username'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $privilege = 'client';  // Default privilege

    // Check if username already exists
    $checkSql = "SELECT * FROM Users WHERE username = ?";
    $stmt = $con->prepare($checkSql);
    $stmt->bind_param("s", $username);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        // Username already exists
        $errorMessage = "Username already taken. Please choose another.";
    } else {
        // Insert new user into the database
        $insertSql = "INSERT INTO Users (username, email, password, privilege) VALUES (?, ?, ?, ?)";
        $stmt = $con->prepare($insertSql);
        $stmt->bind_param("ssss", $username, $email, $password, $privilege);
        $stmt->execute();

        // Check if insertion was successful
        if ($stmt->affected_rows > 0) {
            $successMessage = "Registration successful! You can now <a href='login.php'>login</a>.";
        } else {
            $errorMessage = "Registration failed. Please try again.";
        }

        // Close statement
        $stmt->close();
    }

    // Close database connection
    $con->close();
}
?>

<!-- Main Content -->
<div class="container mt-5">
    <h2 class="text-center mb-4">Register</h2>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <!-- Display success message if any -->
            <?php if (!empty($successMessage)): ?>
                <div class="alert alert-success"><?php echo $successMessage; ?></div>
            <?php endif; ?>

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
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
        </div>
    </div>
</div>

<?php
// Include footer
include('footer.php');
?>
