<?php include('header.php'); ?>

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-8">
      <h2 class="text-center mb-4">Add Product</h2>
      <form action="insert.php" method="post" enctype="multipart/form-data">
        <div class="mb-3">
          <label for="name" class="form-label">Product Name</label>
          <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <div class="mb-3">
          <label for="price" class="form-label">Product Price</label>
          <input type="text" id="price" name="price" class="form-control" required>
        </div>
        <div class="mb-3">
          <label for="file" class="form-label">Choose Product Image</label>
          <input type="file" id="file" name="image" class="form-control" required>
        </div>
        <button type="submit" name="upload" class="btn btn-primary">Upload Product ✔️</button>
      </form>
      <div class="text-center mt-4">
        <a href="index.php" class="btn btn-secondary">View All Products</a>
      </div>
    </div>
  </div>
</div>

<?php include('footer.php'); ?>
