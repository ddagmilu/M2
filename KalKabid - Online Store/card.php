<?php include('header.php'); ?>

<!-- Main Content -->
<div class="container mt-5">
  <h3 class="text-center mb-4">Your Reserved Products</h3>

  <?php
  include('config.php');

  // Query to fetch all products from the cart
  $sql = "SELECT * FROM Card";
  $result = mysqli_query($con, $sql);

  // Initialize total price
  $totalPrice = 0;

  echo "
  <table class='table table-striped'>
    <thead>
      <tr>
        <th scope='col'>Image</th>
        <th scope='col'>Product Name</th>
        <th scope='col'>Product Price</th>
        <th scope='col'>Delete Product</th>
      </tr>
    </thead>
    <tbody>";

  while ($row = mysqli_fetch_array($result)) {
    // Extract numeric value from price string
    $priceString = $row['price'];
    // Remove any non-numeric characters except for decimal points
    $price = floatval(preg_replace('/[^\d.]/', '', $priceString));
    $totalPrice += $price;

    // Get the image URL
    $imageURL = $row['image']; // Ensure this column contains valid image paths

    // Debugging output to check image URLs
    echo "<!-- Debug: Image URL: $imageURL -->";

    echo "
      <tr>
        <td>
          <img src='$imageURL' alt='{$row['name']}' class='img-fluid' style='max-width: 150px;'>
        </td>
        <td>{$row['name']}</td>
        <td>\${$price}</td>
        <td>
          <a href='del_card.php?id={$row['id']}' class='btn btn-danger'>Remove</a>
        </td>
      </tr>";
  }

  echo "
    </tbody>
    <tfoot>
      <tr>
        <td><strong>Total</strong></td>
        <td><strong>\${$totalPrice}</strong></td>
        <td></td> <!-- Empty cell for alignment -->
      </tr>
    </tfoot>
  </table>";

  // Close the database connection
  mysqli_close($con);
  ?>

  <div class="text-center mt-4">
    <a href="index.php" class="btn btn-primary">Back to Products</a>
  </div>
</div>
<br>
<?php include('footer.php'); ?>
