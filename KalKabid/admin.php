<?php
session_start();
require("config.php");

// TODO: Add authentication check here
// if (!isset($_SESSION['admin_logged'])) {
//     header('Location: login.php');
//     exit();
// }

// Handle form submissions for editing and deleting
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    if (isset($_POST['edit'])) {
        // Handle the edit form submission
        $id = $_POST['id'];
        $name = $conn->real_escape_string(trim($_POST['name']));
        $email = $conn->real_escape_string(trim($_POST['email']));
        $date = $conn->real_escape_string(trim($_POST['date']));

        $stmt = $conn->prepare("UPDATE patients SET name = ?, email = ?, date = ? WHERE id = ?");
        $stmt->bind_param("sssi", $name, $email, $date, $id);
        $stmt->execute();
        $stmt->close();
    } elseif (isset($_POST['delete'])) {
        // Handle the delete form submission
        $id = $_POST['id'];
        $stmt = $conn->prepare("DELETE FROM patients WHERE id = ?");
        $stmt->bind_param("i", $id);
        $stmt->execute();
        $stmt->close();
    }
}

// Fetch patients data
$query = "SELECT * FROM patients ORDER BY id ASC";
$stmt = $conn->prepare($query);
$stmt->execute();
$result = $stmt->get_result();

$stmt->close();
$conn->close();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Al Shifa Hospital</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css" rel="stylesheet">
    <script>
        function openEditModal(id, name, email, date) {
            document.getElementById('edit-id').value = id;
            document.getElementById('edit-name').value = name;
            document.getElementById('edit-email').value = email;
            document.getElementById('edit-date').value = date;
            document.getElementById('editModal').classList.remove('hidden');
        }

        function openDeleteModal(id) {
            document.getElementById('delete-id').value = id;
            document.getElementById('deleteModal').classList.remove('hidden');
        }

        function closeModals() {
            document.getElementById('editModal').classList.add('hidden');
            document.getElementById('deleteModal').classList.add('hidden');
        }
    </script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto px-4 py-8">
        <header class="bg-white shadow rounded-lg mb-8 p-6">
            <div class="flex items-center space-x-4">
                <img src="images/cifa logo.png" alt="Al Shifa Hospital Logo" class="w-16 h-auto">
                <h1 class="text-2xl font-bold text-gray-800">Cifa Hospital - Administration Dashboard</h1>
            </div>
        </header>

        <main class="bg-white shadow rounded-lg p-6">
            <h2 class="text-xl font-semibold mb-4">Patient Appointments</h2>
            <?php if ($result->num_rows > 0): ?>
                <div class="overflow-x-auto">
                    <table class="min-w-full bg-white">
                        <thead class="bg-gray-100">
                            <tr>
                                <th class="py-2 px-4 border-b text-left">ID</th>
                                <th class="py-2 px-4 border-b text-left">Patient Name</th>
                                <th class="py-2 px-4 border-b text-left">Email</th>
                                <th class="py-2 px-4 border-b text-left">Appointment Date</th>
                                <th class="py-2 px-4 border-b text-left">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php while ($row = $result->fetch_assoc()): ?>
                                <tr>
                                    <td class="py-2 px-4 border-b"><?php echo htmlspecialchars($row['id']); ?></td>
                                    <td class="py-2 px-4 border-b"><?php echo htmlspecialchars($row['name']); ?></td>
                                    <td class="py-2 px-4 border-b"><?php echo htmlspecialchars($row['email']); ?></td>
                                    <td class="py-2 px-4 border-b"><?php echo htmlspecialchars($row['date']); ?></td>
                                    <td class="py-2 px-4 border-b text-center">
                                        <button onclick="openEditModal(<?php echo htmlspecialchars($row['id']); ?>, '<?php echo htmlspecialchars($row['name']); ?>', '<?php echo htmlspecialchars($row['email']); ?>', '<?php echo htmlspecialchars($row['date']); ?>')" class="text-blue-600 hover:text-blue-800">Edit</button>
                                        <button onclick="openDeleteModal(<?php echo htmlspecialchars($row['id']); ?>)" class="text-red-600 hover:text-red-800 ml-4">Remove</button>
                                    </td>
                                </tr>
                            <?php endwhile; ?>
                        </tbody>
                    </table>
                </div>
            <?php else: ?>
                <p class="text-gray-600">No appointments found.</p>
            <?php endif; ?>
        </main>

        <!-- Edit Modal -->
        <div id="editModal" class="fixed inset-0 flex items-center justify-center bg-gray-500 bg-opacity-50 hidden">
            <div class="bg-white p-6 rounded-lg shadow-lg max-w-sm w-full">
                <h3 class="text-lg font-semibold mb-4">Edit Patient</h3>
                <form method="POST">
                    <input type="hidden" id="edit-id" name="id">
                    <div class="mb-4">
                        <label for="edit-name" class="block text-sm font-medium text-gray-700">Patient Name</label>
                        <input type="text" id="edit-name" name="name" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-300 focus:ring focus:ring-blue-200 focus:ring-opacity-50">
                    </div>
                    <div class="mb-4">
                        <label for="edit-email" class="block text-sm font-medium text-gray-700">Email Address</label>
                        <input type="email" id="edit-email" name="email" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-300 focus:ring focus:ring-blue-200 focus:ring-opacity-50">
                    </div>
                    <div class="mb-4">
                        <label for="edit-date" class="block text-sm font-medium text-gray-700">Preferred Date</label>
                        <input type="date" id="edit-date" name="date" required class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-300 focus:ring focus:ring-blue-200 focus:ring-opacity-50">
                    </div>
                    <div class="flex justify-between">
                        <button type="submit" name="edit" class="bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50">Save</button>
                        <button type="button" onclick="closeModals()" class="bg-gray-600 text-white py-2 px-4 rounded-md hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-gray-500 focus:ring-opacity-50">Cancel</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- Delete Modal -->
        <div id="deleteModal" class="fixed inset-0 flex items-center justify-center bg-gray-500 bg-opacity-50 hidden">
            <div class="bg-white p-6 rounded-lg shadow-lg max-w-sm w-full">
                <h3 class="text-lg font-semibold mb-4">Confirm Delete</h3>
                <p class="mb-4">Are you sure you want to delete this patient?</p>
                <form method="POST">
                    <input type="hidden" id="delete-id" name="id">
                    <div class="flex justify-between">
                        <button type="submit" name="delete" class="bg-red-600 text-white py-2 px-4 rounded-md hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-opacity-50">Delete</button>
                        <button type="button" onclick="closeModals()" class="bg-gray-600 text-white py-2 px-4 rounded-md hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-gray-500 focus:ring-opacity-50">Cancel</button>
                    </div>
                </form>
            </div>
        </div>

        <footer class="mt-8 text-center text-gray-600">
            <p>&copy; 2024 Cifa Hospital.</p>
        </footer>
    </div>
</body>
</html>
