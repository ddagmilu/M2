<?php include('header.php'); ?>

<div class="container mt-4">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h3 class="roboto-medium">Available Products</h3>
    <div>
      <button id="toggleView" class="btn btn-primary">Toggle View</button>
    </div>
  </div>

  <div id="productTableView" class="table-responsive">
    <table class="table table-striped">
      <thead class="thead-dark">
        <tr>
          <th scope="col">Image</th>
          <th scope="col">Name</th>
          <th scope="col">Price</th>
          <th scope="col">Action</th>
        </tr>
      </thead>
      <tbody>
        <?php
        include('config.php');
        $result = mysqli_query($con, "SELECT * FROM Production");
        while ($row = mysqli_fetch_array($result)) {
          echo "<tr>
                  <td><img src='$row[image]' alt='$row[name]' class='img-thumbnail' style='width: 100px; height: auto;'></td>
                  <td>$row[name]</td>
                  <td>$row[price] €</td>
                  <td><a href='val.php?id=$row[id]' class='btn btn-success'>Add to Cart</a></td>
                </tr>";
        }
        ?>
      </tbody>
    </table>
  </div>

  <div id="productListView" class="d-none">
    <div class="row">
      <?php
      include('config.php');
      $result = mysqli_query($con, "SELECT * FROM Production");
      while ($row = mysqli_fetch_array($result)) {
        echo "<div class='col-md-4 mb-4'>
                <div class='card h-100'>
                  <img src='$row[image]' class='card-img-top product-image' alt='$row[name]'>
                  <div class='card-body'>
                    <h5 class='card-title'>$row[name]</h5>
                    <p class='card-text'>Price: $row[price] €</p>
                    <a href='val.php?id=$row[id]' class='btn btn-success w-100'>Add to Cart</a>
                  </div>
                </div>
              </div>";
      }
      ?>
    </div>
  </div>
</div>

<?php include('footer.php'); ?>

<script>
  const toggleButton = document.getElementById('toggleView');
  const tableView = document.getElementById('productTableView');
  const listView = document.getElementById('productListView');

  toggleButton.addEventListener('click', function() {
    tableView.classList.toggle('d-none');
    listView.classList.toggle('d-none');
    toggleButton.textContent = tableView.classList.contains('d-none') ? 'Table View' : 'List View';
  });
</script>
