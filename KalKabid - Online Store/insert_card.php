<?php
include('config.php');

if (isset($_POST['add'])) {
  $name = $_POST['name'];
  $price = $_POST['price'];
  $insert = "INSERT INTO Card (name,price) VALUES ('$name', '$price')";
  mysqli_query($con, $insert);
  header('Location: card.php');
}
