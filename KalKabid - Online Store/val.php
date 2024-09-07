<?php
include('config.php');
$id = $_GET['id'];
$up = mysqli_query($con, "SELECT * FROM Production WHERE id = $id ");
$data = mysqli_fetch_array($up);
?>

<?php include('header.php'); ?>

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card shadow-sm">
        <div class="card-body text-center">
          <h2 class="card-title mb-4">Confirm Product Purchase</h2>
          <img src="<?php echo $data['image'] ?>" alt="Product Image" class="img-fluid mb-3" style="max-width: 100%; height: auto;">
          <form action="insert_card.php" method="post">
            <input type="hidden" name="id" value="<?php echo $data['id'] ?>">
            <input type="hidden" name="name" value="<?php echo $data['name'] ?>">
            <input type="hidden" name="price" value="<?php echo $data['price'] ?>">
            <button name="add" type="submit" class="btn btn-warning btn-lg">Confirm Add to Cart</button>
          </form>
          <div class="mt-3">
            <a href="index.php" class="btn btn-secondary">Back to Products</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<?php include('footer.php'); ?>
