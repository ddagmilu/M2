<?php
// config.php

// Database configuration
$dbConfig = [
    'host' => 'localhost',
    'username' => '********',
    'password' => '********',
    'dbname' => 'hospital'
];

// Create a connection
$conn = new mysqli($dbConfig['host'], $dbConfig['username'], $dbConfig['password'], $dbConfig['dbname']);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>
