<?PHP
require("config.php");
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Al Shifa Hospital - Book an Appointment</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto px-4 py-8">
        <header class="text-center mb-8">
            <img src="images/cifa logo.png" alt="Al Shifa Hospital Logo" class="mx-auto mb-4" style="width: 150px; height: auto;">
            <h1 class="text-3xl font-bold text-gray-800">Cifa Hospital مستشفى الشفاء</h1>
        </header>

        <main>
            <section class="bg-white rounded-lg shadow-md p-8 max-w-md mx-auto">
                <h2 class="text-2xl font-semibold mb-6 text-center">Book an Appointment</h2>

                <form id="appointmentForm" method="POST" class="space-y-4">
                    <div>
                        <label for="name" class="block text-sm font-medium text-gray-700">Full Name</label>
                        <input type="text" id="name" name="name" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-300 focus:ring focus:ring-blue-200 focus:ring-opacity-50">
                    </div>
                    <div>
                        <label for="email" class="block text-sm font-medium text-gray-700">Email Address</label>
                        <input type="email" id="email" name="email" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-300 focus:ring focus:ring-blue-200 focus:ring-opacity-50">
                    </div>
                    <div>
                        <label for="date" class="block text-sm font-medium text-gray-700">Preferred Date</label>
                        <input type="date" id="date" name="date" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-300 focus:ring focus:ring-blue-200 focus:ring-opacity-50">
                    </div>
                    <div>
                        <button type="submit" name="send" class="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50">Book Appointment</button>
                    </div>
                </form>

                <?php

                if ($_SERVER['REQUEST_METHOD'] == 'POST') {
                    // Capture and sanitize user input
                    $patientName = $conn->real_escape_string(trim($_POST['name']));
                    $patientEmail = $conn->real_escape_string(trim($_POST['email']));
                    $appointmentDate = $conn->real_escape_string(trim($_POST['date']));

                    // Use prepared statements to prevent SQL injection
                    $stmt = $conn->prepare("INSERT INTO patients (name, email, date) VALUES (?, ?, ?)");
                    $stmt->bind_param("sss", $patientName, $patientEmail, $appointmentDate);

                    if ($stmt->execute()) {
                        echo "<p class='text-green-600 text-center font-semibold'>Appointment booked successfully.</p>";
                    } else {
                        echo "<p class='text-red-600 text-center font-semibold'>Sorry, Try again.</p>";
                    }

                    $stmt->close();
                }

                $conn->close();
                ?>

            </section>
        </main>

        <footer class="text-center text-gray-600 mt-8">
            <p>&copy; 2024 Cifa Hospital.</p>
        </footer>
    </div>
</body>
</html>
