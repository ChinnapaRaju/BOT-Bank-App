<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Online Banking Portal</title>
<style>
/* General Styling */
body {
    font-family: Arial, sans-serif;
    background-color: #f0f8f8;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    flex-direction: column;
    margin: 0;
}

/* Welcome Message */
.welcome-message {
    text-align: center;
    font-size: 28px;
    font-weight: bold;
    margin-bottom: 20px;
    color: #007bff; /* Blue color */
}

.welcome-message span {
    color: black; /* Black for 'BOT' */
}

/* Login Container */
.container {
    width: 30%;
    background-color: #e6efef;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0px 0px 10px #aaa;
    text-align: left;
}

/* Form Layout */
.form-group {
    display: flex;
    align-items: center;
    justify-content: flex-start;
    margin-bottom: 12px;
    margin-left: 10%;
}

.form-group label {
    width: 30%;
    text-align: right;
    font-weight: bold;
    margin-right: 10px;
}

.form-group input {
    width: 50%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 10px;
}

/* Error Message Styling */
.error {
    color: red;
    font-size: 14px;
    margin-left: 40%;
    margin-top: 3px;
}

/* Buttons */
.button {
    background-color: #007bff;
    color: white;
    padding: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    width: 100%;
    margin-top: 10px;
}

.button:hover {
    background-color: #0056b3;
}

/* Footer */
.footer {
    margin-top: 10px;
    text-align: center;
}

.footer a {
    color: #007bff;
    text-decoration: none;
    margin: 20px;
}

.footer a:hover {
    text-decoration: underline;
}

.fpassword {
    display: block;
    text-align: right;
    margin-right: 20%;
    margin-top: 0px;
}
</style>
<script>
function validateForm(event) {
    let customerId = document.getElementById("customerid").value;
    let password = document.getElementById("customerpassword").value;
    let customerIdError = document.getElementById("customeridError");
    let passwordError = document.getElementById("customerpasswordError");
    let isValid = true;

    // Password Validation (6-20 chars, one uppercase, one lowercase, one number, one special character)
    let passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,20}$/;
    if (!passwordPattern.test(password)) {
        passwordError.textContent = "Password must be 6-20 chars, include uppercase, lowercase, number, and special character.";
        isValid = false;
    } else {
        passwordError.textContent = "";
    }

    // Customer ID Validation (Only Numbers, Exactly 9 Digits)
    if (!/^\d{9}$/.test(customerId)) {
        customerIdError.textContent = "Please enter a valid 9-digit Customer ID.";
        isValid = false;
    } else {
        customerIdError.textContent = "";
    }

    if (!isValid) {
        event.preventDefault(); // Prevent form submission if validation fails
    }
}
</script>
</head>
<body>

    <!-- Welcome Message -->
    <h1 class="welcome-message"> welcome to <span style="color: green;">BOT</span> internet bank  </h1><br><br>

    <div class="container">
        <h1 align="center">Login</h1>

        <!-- Error message displayed only after a failed attempt -->
        <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
        %>
        <p class="error"><%=errorMessage%></p>
        <%
        }
        %>

        <form action="homepagecontroller" method="get" onsubmit="validateForm(event)">
            <!-- Customer ID Field -->
            <div class="form-group">
                <label for="customerid">Customer ID:</label> 
                <input type="text" id="customerid" name="customerid" required>
            </div>
            <span class="error" id="customeridError"></span>

            <!-- Password Field -->
            <div class="form-group">
                <label for="customerpassword">Password:</label> 
                <input type="password" id="customerpassword" name="customerpassword" required>
            </div>
            <span class="error" id="customerpasswordError"></span> 
            <a href="#" class="fpassword">Forgot Password?</a> 
            <br><br>
            <button type="submit" class="button">Login</button>
            <button type="button" class="button" onclick="window.location.href='registrationpage'">New User Registration</button>
        </form>

        <div class="footer">
            <a href="#">Help</a> 
            <a href="#">About Us</a> 
            <a href="#">Privacy Policy</a>
        </div>
    </div>

</body>
</html>
