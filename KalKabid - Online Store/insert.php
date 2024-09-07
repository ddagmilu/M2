<?php
include('config.php');

if (isset($_POST['upload'])) {
  $name = $_POST['name'];
  $price = $_POST['price'];
  $image_location = $_FILES['image']['tmp_name'];
  $image_name = $_FILES['image']['name'];
  $image_up = "images/" . $image_name;

  $insert = "INSERT INTO Production (name, price, image) VALUES ('$name','$price','$image_up')";

  if (mysqli_query($con, $insert)) {
    if (move_uploaded_file($image_location, $image_up)) {
      header('Location: products.php?status=success');
      exit();
    } else {
      header('Location: products.php?status=image_error');
      exit();
    }
  } else {
    header('Location: products.php?status=db_error');
    exit();
  }
}
