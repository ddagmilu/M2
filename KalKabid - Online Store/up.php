<?php
ob_start(); // Start output buffering
include('config.php');

if (isset($_POST['update'])) {
    $id = $_POST['id'];
    $name = $_POST['name'];
    $price = $_POST['price'];

    if (isset($_FILES['image']) && $_FILES['image']['error'] == 0) {
        $image_temp_location = $_FILES['image']['tmp_name'];
        $image_name = $_FILES['image']['name'];
        $image_destination = "images/" . $image_name;

        if (move_uploaded_file($image_temp_location, $image_destination)) {
            $update_query = "UPDATE Production SET name = '$name', price = '$price', image = '$image_destination' WHERE id = $id";
            mysqli_query($con, $update_query);
            echo "<script>alert('Product updated successfully.')</script>";
        } else {
            echo "<script>alert('Error occurred while uploading the image.')</script>";
        }
    } else {
        $update_query = "UPDATE Production SET name = '$name', price = '$price' WHERE id = $id";
        mysqli_query($con, $update_query);
        echo "<script>alert('Product updated successfully without updating the image.')</script>";
    }

    if (!headers_sent()) {
        header('Location: products.php');
        exit();
    } else {
        echo "<script>alert('Error occurred while redirecting to the products page.')</script>";
    }
}

ob_end_flush(); // End output buffering and send output
