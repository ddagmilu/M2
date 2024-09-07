<?php

include('config.php');

$id = $_GET['id'];

mysqli_query($con, "DELETE FROM Production WHERE id= $id");

header('Location: products.php');

?>
