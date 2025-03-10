<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Registration</title>
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 70px 0; /* Added space at the top and bottom */
        }

        .container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 500px;
            text-align: center;
            margin: 40px 0; /* Space above and below the form */
        }

        h2 {
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
            text-align: left;
        }

        label {
            font-weight: bold;
            margin: 10px 0 5px;
        }

        input, select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        input[type="submit"] {
            margin-top: 15px;
            background-color: #28a745;
            color: white;
            font-size: 16px;
            cursor: pointer;
            border: none;
            padding: 10px;
            border-radius: 4px;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .alert {
            padding: 10px;
            margin-bottom: 15px;
            font-weight: bold;
        }

        .alert-success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .alert-danger {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        .login-btn {
            display: inline-block;
            margin-top: 15px;
            padding: 10px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            font-size: 16px;
            border-radius: 4px;
        }

        .login-btn:hover {
            background-color: #0056b3;
        }
        
        /* Added for validation messages */
        .validation-error {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Customer Registration</h2>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
        <% } %>

        <% if (request.getAttribute("message") != null) { %>
            <div class="alert alert-success"><%= request.getAttribute("message") %></div>
        <% } %>

        <form action="registercustomer" method="post" id="registerForm">
            <label>Full Name:</label>
            <input type="text" name="fullname" id="fullname" required>
            <div class="validation-error" id="fullnameError"></div>

            <label>Phone Number:</label>
            <input type="text" name="customerphonenumber" id="phonenumber" required>
            <div class="validation-error" id="phonenumberError"></div>

            <label>Email:</label>
            <input type="email" name="customermail" id="email" required>
            <div class="validation-error" id="emailError"></div>

            <label>Password:</label>
            <input type="password" name="customerpassword" id="password" required>
            <div class="validation-error" id="passwordError"></div>

            <label>Aadhaar Number:</label>
            <input type="text" name="aadhaarnumber" id="aadhaar" required>
            <div class="validation-error" id="aadhaarError"></div>

            <label>PAN Number:</label>
            <input type="text" name="pannumber" id="pan" required>
            <div class="validation-error" id="panError"></div>

            <label>Address:</label>
            <input type="text" name="address" id="address" required>
            <div class="validation-error" id="addressError"></div>

            <label>Date of Birth (YYYY-MM-DD):</label>
            <input type="date" name="dateofbirth" id="dob" required>
            <div class="validation-error" id="dobError"></div>

            <label>Gender:</label>
            <select name="gender" id="gender" required>
                <option value="M">Male</option>
                <option value="F">Female</option>
                <option value="O">Other</option>
            </select>
            <div class="validation-error" id="genderError"></div>

            <input type="submit" value="Register">
        </form>

        <a href="welcomepage" class="login-btn">Already have an account? Login</a>
    </div>

    <script>
        document.getElementById('registerForm').addEventListener('submit', function(e) {
            let isValid = true;

            // Full Name Validation
            const fullname = document.getElementById('fullname').value;
            if (!/^[A-Za-z .]+$/.test(fullname)) {
                document.getElementById('fullnameError').innerText = 'Only letters, spaces, and periods are allowed.';
                isValid = false;
            } else {
                document.getElementById('fullnameError').innerText = '';
            }

            // Phone Number Validation
            const phonenumber = document.getElementById('phonenumber').value;
            if (!/^\d{10}$/.test(phonenumber)) {
                document.getElementById('phonenumberError').innerText = 'Phone number must be 10 digits.';
                isValid = false;
            } else {
                document.getElementById('phonenumberError').innerText = '';
            }

            // Email Validation
            const email = document.getElementById('email').value;
            if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)) {
                document.getElementById('emailError').innerText = 'Invalid email format.';
                isValid = false;
            } else {
                document.getElementById('emailError').innerText = '';
            }

            // Password Validation
            const password = document.getElementById('password').value;
            if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,30}$/.test(password)) {
                document.getElementById('passwordError').innerText = 'Password must be 8-30 characters, with at least one uppercase, one lowercase, and one digit.';
                isValid = false;
            } else {
                document.getElementById('passwordError').innerText = '';
            }

            // Aadhaar Number Validation
            const aadhaar = document.getElementById('aadhaar').value;
            if (!/^\d{12}$/.test(aadhaar)) {
                document.getElementById('aadhaarError').innerText = 'Aadhaar number must be exactly 12 digits.';
                isValid = false;
            } else {
                document.getElementById('aadhaarError').innerText = '';
            }

            // PAN Number Validation
            const pan = document.getElementById('pan').value;
            if (!/^[A-Z]{5}[0-9]{4}[A-Z]{1}$/.test(pan)) {
                document.getElementById('panError').innerText = 'Invalid PAN format.';
                isValid = false;
            } else {
                document.getElementById('panError').innerText = '';
            }

            // Address Validation
            const address = document.getElementById('address').value;
            if (address.length > 100) {
                document.getElementById('addressError').innerText = 'Address cannot exceed 100 characters.';
                isValid = false;
            } else {
                document.getElementById('addressError').innerText = '';
            }

            // Date of Birth Validation
            const dob = new Date(document.getElementById('dob').value);
            const today = new Date();
            if (dob >= today) {
                document.getElementById('dobError').innerText = 'Date of birth must be in the past.';
                isValid = false;
            } else {
                document.getElementById('dobError').innerText = '';
            }

            if (!isValid) {
                e.preventDefault();
            }
        });
    </script>

</body>
</html>
