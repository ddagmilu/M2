<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);

$client = new SoapClient('http://localhost:8080/Calculator_WebService/Calculator_WebService?wsdl');


//$functions = $client->__getFunctions ();
//echo "<pre>";
//var_dump ($functions);
//echo "</pre>";

$activeTab = isset($_POST['active_tab']) ? $_POST['active_tab'] : 'tab0'; // Default tab when none is set

$functions = '';
$resultValue = '';
$PrimeOrNot = '';
$roots = '';
$currencies = '';
$converted_value = '';


// 0. Get functions
$result = $client->__getFunctions ();
$functions = $result;



// 1. Sum of 2 Numbers
if (isset($_POST['submit_sum'])) {
    $params = array(
        'Num_1' => $_POST['inputA'],
        'Num_2' => $_POST['inputB']
    );
    $result = $client->Sum($params);
    $resultValue = $result->return;
}

// 2. Prime?
if (isset($_POST['submit_prime'])) {
    $params = array(
        'Num' => $_POST['inputD']
    );
    $result = $client->Prime_Check($params);
    if ($result->return) {
        $PrimeOrNot = "Prime !";
    } else {
        $PrimeOrNot = "Not Prime !";
    }
}

// 3. II Degre equation
if (isset($_POST['submit_equation'])) {
    $params = array(
        'a' => $_POST['inputA'],
        'b' => $_POST['inputB'],
        'c' => $_POST['inputB']
    );
    $result = $client->SecondDegreEquation($params);
    //$resultValue = $result->return;
    $roots = implode( ", ", $result->return );
}


// 4.0 Currency converter
if (isset($_POST['submit_currency'])) {
    $params = array(
        'Value1' => $_POST['inputA'],
        'Currency1' => $_POST['Currency1'],
        'Currency2' => $_POST['Currency2']
    );
    $result = $client->CurrencyConverter($params);
    #var_dump($result);
    $converted_value = $_POST['inputA'] . " " . $_POST['Currency1'] . " &rarr; " . $result->return . " " . $_POST['Currency2'];
}
// 4.1 Load Currencies options
$result = $client->Currencies();
if (isset($result->return)) {
    $currencies = $result->return;
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>SOAP Web Services</title>
<style>
  /* Centered layout */
  body {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: #f7f7f7;
    margin: 0;
    padding-top: 20px; /* Adjust as needed for space between logo and content */
  }

  /* Tab styles */
  .tabbed {
    max-width: 600px;
    width: 100%;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    background-color: #fafafa;
    border-radius: 8px;
    padding: 20px;
    box-sizing: border-box;
  }

  .tab {
    display: none;
  }

  .tab.active {
    display: block;
  }

  button {
    background-color: #007bff;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    color: #fff;
    border-radius: 8px;
    margin: 5px;
  }

  button:hover {
    background-color: #0056b3;
  }

    .tabbed .custom-combobox {
    position: relative;
    display: inline-block;
    margin-bottom: 15px; /* Adjust margin as needed */
  }

  .tabbed .custom-combobox select {
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    background-color: #f0f0f0;
    border: 1px solid #ccc;
    padding: 5px 10px 8px 10px;
    font-size: 14px;
    border-radius: 5px;
    cursor: pointer;
    width: 80px;
    outline: none;
  }

  /* Arrow icon */
  .tabbed .custom-combobox::after {
    content: '\25BC';
    position: absolute;
    top: 50%;
    right: 10px;
    transform: translateY(-50%);
    pointer-events: none;
  }
  .centered-logo {
    text-align: center;
  }
  .centered-logo img {
    width: 50%; /* Adjust the width as needed */
    height: auto; /* Adjust the height as needed */
  }
  ul {
    list-style-type: none;
    padding: 0;
    margin: 0;
    }
  li {
    text-align: left;
    padding: 8px 0;
    border-bottom: 1px solid #ccc;
    }
  li:last-child {
    text-align: left;
    border-bottom: none;
    }
</style>

<div class="centered-logo">
        <img src="SW_TP_Logo.png" alt="TP Logo">
</div>

</head>
<body>
  <div class="tabbed">
    <button onclick="openTab(event, 'tab0')">Functions</button>
    <button onclick="openTab(event, 'tab1')">Sum</button>
    <button onclick="openTab(event, 'tab2')">Prime?</button>
    <button onclick="openTab(event, 'tab3')">II Degre Equation</button>
    <button onclick="openTab(event, 'tab4')">Currency Converter</button>

    <div id="tab0" class="tab <?php echo $activeTab === 'tab0' ? 'active' : ''; ?>">
    <center>
      <h2>Available Functions</h2>
      <form method="post" id="functionsForm">
        <div>
        <input type="hidden" name="active_tab" value="tab0">
          <ul>
            <?php
            foreach ($functions as $function) {
              echo "<li>". $function . "</li>";
            }
            ?>
          </ul>
        </div>
      </form>
      </center>
    </div>
    <div id="tab1" class="tab <?php echo $activeTab === 'tab1' ? 'active' : ''; ?>">
    <center>
      <h2>Calculate Sum</h2>
      <form method="post" id="sumForm">
        <input type="hidden" name="active_tab" value="tab1">
        <label for="inputA">\(a\)</label>
        <input type="number" id="inputA" name="inputA" required size="8">
        <label>+</label>
        <label for="inputB">\(b\)</label>
        <input type="number" id="inputB" name="inputB" required size="8">
        <label>=</label>
        <b><label><?php echo $resultValue; ?></label></b>
        <br><br><button type="submit_sum" name="submit_sum">Calculate</button>
      </form>
    </center>
    </div>

    <div id="tab2" class="tab <?php echo $activeTab === 'tab2' ? 'active' : ''; ?>">
    <center>
      <h2>Prime or not?</h2>
      <form method="post" id="primeForm">
      <input type="hidden" name="active_tab" value="tab2">
        <label for="inputD">Number : </label>
        <input type="number" id="inputD" name="inputD" required size="8">
        <b><label><?php echo $PrimeOrNot; ?></label></b>
        <br><br><button type="submit_prime" name="submit_prime">Check</button>
      </form>
      </center>
    </div>

    <div id="tab3" class="tab <?php echo $activeTab === 'tab3' ? 'active' : ''; ?>">
    <center>
      <h2>II Degre Equation Solver</h2>
      <form method="post" id="secdegForm">
        <input type="hidden" name="active_tab" value="tab3">
        <input type="number" id="inputA" name="inputA" required size="8">
        <label for="inputA">\(x^2\)</label>
        <label>+</label>
        <input type="number" id="inputB" name="inputB" required size="8">
        <label for="inputB">\(x\)</label>
        <label>+</label>
        <input type="number" id="inputC" name="inputC" required size="8">
        <label> ? </label>
        <b><label><?php echo $roots;  ?></label></b>
        <br><br><button type="submit_equation" name="submit_equation">Calculate</button>
      </form>
      </center>
    </div>

    <div id="tab4" class="tab <?php echo $activeTab === 'tab4' ? 'active' : ''; ?>">
    <center>
      <h2>Currency Converter</h2>
      <form method="post" id="currencyForm">
        <input type="hidden" name="active_tab" value="tab4">
        <input type="number" id="inputA" name="inputA" required size="10">
        <div class="custom-combobox">
            <select name="Currency1" id="Currency1">
            <?php
            foreach ($currencies as $currency) {
              echo '<option value="' . $currency . '">' . $currency . '</option>';
            }
            ?>
            </select>
        </div>
        <label> &rarr; </label>
        <div class="custom-combobox">
            <select name="Currency2" id="Currency2">
            <?php
            foreach ($currencies as $currency) {
              echo '<option value="' . $currency . '">' . $currency . '</option>';
            }
            ?>
            </select>
        </div><br>
        <b><label size=10><?php echo $converted_value; ?></label></b>
        <p id="result"></p>
        <br><br><button type="submit_currency" name="submit_currency">Convert</button>
      </form>
      </center>
    </div>
  </div>

  <script type="text/javascript" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js">
  </script>
  <script>
    function openTab(evt, tabName) {
      var i, tabcontent;
      tabcontent = document.getElementsByClassName("tab");
      for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].classList.remove("active");
      }
      document.getElementById(tabName).classList.add("active");
    }
  </script>
</body>
</html>

