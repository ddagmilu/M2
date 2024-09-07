<?php
// Start session
session_start();
?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Product Listing</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200..1000&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/shop.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Fugaz+One&display=swap" rel="stylesheet">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha512-S7s3YkP+U0SybVZZQ9c6ZBZd/jyGxW8KONm+/xuUMRA9yO8l4p7xQ2jss3/jRQkvsAOW5AZoeDMEe0nBzU8kmQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <div class="d-flex flex-grow-1 justify-content-start">
        <!-- Logo and Username with Logout -->
        <span class="fugaz-one-regular me-3">
          Shopo
        </span>
        <?php if (isset($_SESSION['username'])): ?>
          <div class="d-flex align-items-center me-3">
            <span class="text-light me-2">
              Welcome, <?php echo htmlspecialchars($_SESSION['username']); ?>
            </span>
            <a class="btn btn-outline-light me-2" href="logout.php">Logout</a>
          </div>
        <?php endif; ?>
      </div>
      <div class="d-flex justify-content-end">
        <form class="d-flex me-3" role="search">
          <input class="form-control me-2" type="search" placeholder="Search products..." aria-label="Search">
          <button class="btn btn-outline-light" type="submit">Search</button>
        </form>
        <a class="navbar-brand" href="card.php">MyCart</a>
        <?php if (!isset($_SESSION['username'])): ?>
          <a class="navbar-brand" href="login.php">Login</a>
          <a class="btn btn-outline-light ms-2" href="register.php">Register</a>
        <?php endif; ?>
      </div>
    </div>
  </nav>
