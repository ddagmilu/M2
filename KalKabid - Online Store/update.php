<?php include('header.php'); ?>

<?php
include('config.php');
$id = $_GET['id'];
$up = mysqli_query($con, "SELECT * FROM Production WHERE id = $id");
$data = mysqli_fetch_array($up);
?>

<div class="container mt-5">
  <div class="row mb-4">
    <div class="col-12 text-center">
      <h2>Update Product</h2>
    </div>
  </div>

  <div class="row justify-content-center">
    <div class="col-md-8">
      <form action="up.php" method="post" enctype="multipart/form-data" class="border p-4 rounded shadow-sm">
        <div class="text-center mb-3">
          <img src="<?php echo $data['image'] ?>" alt="Product Image" class="img-fluid" style="max-width: 100%; height: auto;">
        </div>
        <input type="hidden" name="id" value="<?php echo $data['id'] ?>">
        <div class="mb-3">
          <label for="name" class="form-label">Product Name</label>
          <input type="text" id="name" name="name" class="form-control" value="<?php echo $data['name'] ?>" required>
        </div>
        <div class="mb-3">
          <label for="price" class="form-label">Product Price</label>
          <input type="text" id="price" name="price" class="form-control" value="<?php echo $data['price'] ?>" required>
        </div>
        <div class="mb-3">
          <label for="file" class="form-label">Update Product Image</label>
          <input type="file" id="file" name="image" class="form-control">
        </div>
        <button name="update" type="submit" class="btn btn-primary">Update Product ✏️</button>
        <a href="products.php" class="btn btn-secondary ms-2">Back to Products</a>
      </form>
    </div>
  </div>
</div>

<?php include('footer.php'); ?>
