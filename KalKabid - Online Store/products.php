<?php include('header.php'); ?>

<div class="container mt-5">
  <div class="row mb-4">
    <div class="col-12 text-center">
      <h3>Admin Dashboard</h3>
    </div>
  </div>

  <div class="row">
    <?php
    include('config.php');
    $result = mysqli_query($con, "SELECT * FROM Production");
    while ($row = mysqli_fetch_array($result)) {
      echo "<div class='col-md-4 mb-4'>
        <div class='card'>
          <img src='{$row['image']}' class='card-img-top product-image' alt='{$row['name']}'>
          <div class='card-body'>
            <h5 class='card-title'>{$row['name']}</h5>
            <p class='card-text'>{$row['price']}</p>
            <a href='delete.php?id={$row['id']}' class='btn btn-danger'>Delete Product</a>
            <a href='update.php?id={$row['id']}' class='btn btn-dark'>Edit Product</a>
          </div>
        </div>
      </div>";
    }
    ?>
  </div>

  <div class="text-center mt-4">
    <a href="index.php" class="btn btn-secondary">Back to Store</a>
    <a href="add.php" class="btn btn-primary">Add Product</a>
  </div>
</div>

<?php include('footer.php'); ?>
