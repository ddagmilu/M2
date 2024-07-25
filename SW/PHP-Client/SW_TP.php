<?php

// SOAP Wsdl
$SOAPClient_API = new SoapClient('http://localhost:8080/SW_tp/SW_tp?wsdl');

$Addition = '';
$Premier = '';
$Resolution = '';
$PremierResult = '';
$converted_value = '';

error_reporting(E_ALL);
ini_set('display_errors', 1);

/////////////////////////////// + + + + +
if (isset($_POST['add'])) {
    $Input = array(
        'Numero_1' => $_POST['N1'],
        'Numero_2' => $_POST['N2']
    );
    $RESULT = $SOAPClient_API->Addition($Input);
    $Addition = $RESULT->return;
}

////////////////////////////// Premier..
if (isset($_POST['checkPrime'])) {
    $Input = array(
        'Nombre' => $_POST['checkPrimeNumber']
    );
    $RESULT = $SOAPClient_API->Nombre_Premier($Input);
    $Premier = $RESULT->return;

    if ($Premier)
    {
      $PremierResult = "Oui";

    } else {
      $PremierResult = "Non";
    }
}

/////////////////////////////// Equation
if (isset($_POST['Resolution'])) {
    $Input = array(
        'a' => $_POST['a'],
        'b' => $_POST['b'],
        'c' => $_POST['c']
    );
    $RESULT = $SOAPClient_API->EquationDe2Deg($Input);
    $Resolution = implode( ", ", $RESULT->return );
}
//////////////////////////////// DINAR -> EURO
if (isset($_POST['convert'])) {
    $Input = array(
        'Valeur' => $_POST['Money'],
        'C_1' => $_POST['baseCurrency'],
        'C_2' => $_POST['targetCurrency']
    );
    $RESULT = $SOAPClient_API->Convertisseur($Input);
    $converted_value = $_POST['inputA'] . " " . $_POST['Currency1'] . " &rarr; " . $RESULT->return . " " . $_POST['Currency2'];
}

?>
<!DOCTYPE html>
<html>
<head>
    <title>SW TP SOAP Client</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #222;
            color: #fff;
        }
        .container {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            padding: 20px;
        }
        form {
            flex: 1;
            margin: 20px;
            padding: 20px;
            background-color: #333;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(255, 255, 255, 0.1);
        }
        input[type="text"], input[type="number"], select, input[type="submit"] {
            display: block;
            margin-bottom: 15px;
            width: calc(100% - 40px);
            padding: 10px;
            font-size: 14px;
            border: 1px solid #444;
            border-radius: 3px;
            background-color: #444;
            color: #fff;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        label {
            font-weight: bold;
            color: #ccc;
            margin-bottom: 5px;
            display: block;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Addition -->
        <form action="" method="post">
            <label>Addition</label>
            <input type="text" name="N1" placeholder="First number">
            <input type="text" name="N2" placeholder="Second number">
            <input type="submit" name="add" value="Add">
            <b><label><?php echo $Addition; ?></label></b>
        </form>

        <!-- Check Prime -->
        <form action="" method="post">
            <label>Premier ou Non?</label>
            <input type="text" name="checkPrimeNumber" placeholder="Enter number">
            <input type="submit" name="checkPrime" value="?">
            <b><label><?php echo $PremierResult ?></label></b>
        </form>

        <!-- Equation -->
        <form action="" method="post">
            <label>Equation</label>
            <input type="number" name="a" placeholder="a">
            <input type="number" name="b" placeholder="b">
            <input type="number" name="c" placeholder="c">
            <input type="submit" name="Resolution" value="Resolution">
            <b><label><?php echo $Resolution; ?></label></b>
        </form>

        <!-- Convertisseur -->
        <form action="" method="post">
            <label>Convertisseur</label>
            <input type="text" name="Money" placeholder="Enter value">
            <select name="baseCurrency">
                <option value="DZD">DZD</option>
                <option value="USD">USD</option>
                <option value="EUR">EUR</option>
                <option value="JPY">JPY</option>
                <option value="GBP">GBP</option>
                <option value="AUD">AUD</option>
                <option value="CAD">CAD</option>
                <option value="CHF">CHF</option>
                <option value="CNY">CNY</option>
                <option value="SEK">SEK</option>
                <option value="NZD">NZD</option>
                <option value="KRW">KRW</option>
                <option value="SGD">SGD</option>
                <option value="NOK">NOK</option>
                <option value="MXN">MXN</option>
                <option value="INR">INR</option>
                <option value="RUB">RUB</option>
                <option value="ZAR">ZAR</option>
            </select>
            <select name="targetCurrency">
                <option value="USD">USD</option>
                <option value="EUR">EUR</option>
                <option value="JPY">JPY</option>
                <option value="DZD">DZD</option>
                <option value="GBP">GBP</option>
                <option value="AUD">AUD</option>
                <option value="CAD">CAD</option>
                <option value="CHF">CHF</option>
                <option value="CNY">CNY</option>
                <option value="SEK">SEK</option>
                <option value="NZD">NZD</option>
                <option value="KRW">KRW</option>
                <option value="SGD">SGD</option>
                <option value="NOK">NOK</option>
                <option value="MXN">MXN</option>
                <option value="INR">INR</option>
                <option value="RUB">RUB</option>
                <option value="ZAR">ZAR</option>
            </select>
            <input type="submit" name="convert" value="Convert">
            <b><label><?php echo $converted_value; ?></label></b>
        </form>
    </div>
</body>
</html>
