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
        padding: 70px 0;
    }

    .container {
        background: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        width: 500px;
        text-align: center;
        margin: 40px 0;
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

    input,
    select,
    button {
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 14px;
    }

    input[type="submit"],
    .otp-btn {
        margin-top: 15px;
        background-color: #28a745;
        color: white;
        font-size: 16px;
        cursor: pointer;
        border: none;
        padding: 10px;
        border-radius: 4px;
    }

    input[type="submit"]:hover,
    .otp-btn:hover {
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

    .otp-container {
        display: flex;
        align-items: center;
        gap: 10px;
    }

    .otp-btn {
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
        padding: 8px;
        border-radius: 4px;
    }

    .otp-btn:hover {
        background-color: #0056b3;
    }

    .validation-error {
        color: red;
        font-size: 12px;
        margin-top: 5px;
    }

    /* Style to hide elements initially */
    .hidden {
        display: none;
    }
    </style>
</head>
<body>

    <div class="container">
        <h2>Customer Registration</h2>

        <% if (request.getAttribute("error")!= null) { %>
        <div class="alert alert-danger">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <% if (request.getAttribute("message")!= null) { %>
        <div class="alert alert-success">
            <%= request.getAttribute("message") %>
        </div>
        <% } %>

        <form action="registercustomer" method="post" id="registerForm">
            <label>Full Name:</label>
            <input type="text" name="fullname" id="fullname" required>
            <div class="validation-error" id="fullnameError"></div>

            <label>Phone Number:</label>
            <input type="text" name="customerphonenumber" id="phonenumber" required>
            <div class="validation-error" id="phonenumberError"></div>

            <label>Email:</label>
            <div class="otp-container">
                <input type="email" name="customermail" id="email" required>
                <button type="button" class="otp-btn" onclick="sendOTP()">Send OTP</button>
            </div>
            <div class="validation-error" id="emailError"></div>

            <!-- OTP input initially hidden -->
            <div id="otpSection" class="hidden">
                <label>Enter OTP:</label>
                <input type="text" name="otp" id="otp">
                <div class="validation-error" id="otpError"></div>
                <button type="button" class="otp-btn" onclick="verifyOTP()">Verify OTP</button>
            </div>

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

            <input type="hidden" name="otpVerified" id="otpVerified" value="false">

            <input type="submit" value="Register" id="registerButton" disabled>
        </form>

        <a href="welcomepage" class="login-btn">Already have an account? Login</a>
    </div>

    <script>
    let isOTPVerified = false;

    function sendOTP() {
        const email = document.getElementById("email").value;
        if (!email) {
            document.getElementById("emailError").innerText = "Please enter an email first.";
            return;
        }
        document.getElementById("emailError").innerText = "";

        fetch("http://localhost:8688/customerotp/send?email=" + encodeURIComponent(email), {
                method: "GET"
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("OTP sent to your email.");
                    // Show OTP input section after sending OTP
                    document.getElementById("otpSection").classList.remove("hidden");
                } else {
                    alert("Failed to send OTP: " + data.message);
                }
            })
            .catch(error => {
                console.error("Error sending OTP:", error);
                alert("Error sending OTP. Please check the console.");
            });
    }

    function verifyOTP() {
        const otp = document.getElementById("otp").value;
        const email = document.getElementById("email").value;

        fetch("http://localhost:8688/customerotp/verify?email=" + encodeURIComponent(email) + "&otp=" + encodeURIComponent(otp), {
                method: "GET"
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("OTP verified successfully. You can now register.");
                    isOTPVerified = true;
                    document.getElementById("otpVerified").value = "true";
                    document.getElementById("registerButton").disabled = false; // Enable registration button
                } else {
                    alert("Invalid OTP. Please verify again.");
                    isOTPVerified = false;
                    document.getElementById("otpVerified").value = "false";
                    document.getElementById("registerButton").disabled = true; // Ensure button is disabled
                }
            })
            .catch(error => {
                console.error("Error verifying OTP:", error);
                alert("Error verifying OTP. Please check the console.");
                isOTPVerified = false;
                document.getElementById("otpVerified").value = "false";
                document.getElementById("registerButton").disabled = true; // Ensure button is disabled
            });
    }

    document.getElementById('registerForm').addEventListener('submit', function(e) {
        if (!isOTPVerified) {
            alert("Please verify OTP before registering.");
            e.preventDefault(); // Prevent form submission
        }
    });
    </script>

</body>
</html>
