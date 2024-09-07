<?php

// $con =  mysqli_connect('localhost', 'root', '', 'online');

$con =  mysqli_connect('localhost', 'root', 'root', 'online');
if ($con->connect_error) {
  die("Connection failed: " . $con->connect_error);
}
