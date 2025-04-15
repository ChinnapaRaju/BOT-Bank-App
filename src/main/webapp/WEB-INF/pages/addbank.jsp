<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trading Account Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        
        .container {
            width: 50%;
            margin: auto;
            padding: 20px;
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
        }
        
        td {
            padding: 10px;
            border-bottom: 1px solid #ccc;
        }
        
        td input[type="text"], td select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        
        td input[type="text"]:focus, td select:focus {
            outline: none;
            border-color: #aaa;
            box-shadow: 0 0 5px rgba(0,0,0,0.2);
        }
        
        td span {
            color: red;
            font-size: 12px;
        }
        
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        
        input[type="submit"]:hover {
            background-color: #3e8e41;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Trading Account Form</h2>
        <form action="/addbank" method="post" id="tradingForm">
            <table>
                <tr>
                    <td>Account Number:</td>
                    <td><input type="text" id="accountnumber" name="accountnumber" required pattern="^[A-Z0-9]{10,20}$" title="Account number must be alphanumeric and between 10-20 characters"></td>
                    <td><span id="accountnumberError"></span></td>
                </tr>
                <tr>
                    <td>Bank Name:</td>
                    <td><input type="text" id="bankname" name="bankname" required pattern="^[A-Za-z ]{2,100}$" title="Bank name must contain only letters and spaces (2-100 characters)"></td>
                    <td><span id="banknameError"></span></td>
                </tr>
                <tr>
                    <td>Account Holder Name:</td>
                    <td><input type="text" id="accountholdername" name="accountholdername" required pattern="^[A-Za-z ]{2,50}$" title="Name must contain only letters and spaces (2-50 characters)"></td>
                    <td><span id="accountholdernameError"></span></td>
                </tr>
                <tr>
                    <td>IFSC Code:</td>
                    <td><input type="text" id="ifsc" name="ifsc" required pattern="^[A-Za-z]{4}[0-9]{7}$" title="Invalid IFSC code format"></td>
                    <td><span id="ifscError"></span></td>
                </tr>
                <tr>
                    <td>Account Type:</td>
                    <td>
                        <select name="accounttype" id="accounttype" required>
                            <option value="">Select Account Type</option>
                            <option value="SAVINGS">SAVINGS</option>
                            <option value="CHECKING">CHECKING</option>
                            <option value="MARGIN">MARGIN</option>
                            <option value="INVESTMENT">INVESTMENT</option>
                        </select>
                    </td>
                    <td><span id="accounttypeError"></span></td>
                </tr>
                <tr>
                    <td colspan="3"><input type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>
    </div>

    <script>
        document.getElementById('tradingForm').addEventListener('submit', function(event) {
            let isValid = true;

            // Account Number Validation
            let accountnumber = document.getElementById('accountnumber').value;
            if (!accountnumber.match(/^[A-Z0-9]{10,20}$/)) {
                document.getElementById('accountnumberError').innerText = 'Invalid account number format';
                isValid = false;
            } else {
                document.getElementById('accountnumberError').innerText = '';
            }

            // Bank Name Validation
            let bankname = document.getElementById('bankname').value;
            if (!bankname.match(/^[A-Za-z ]{2,100}$/)) {
                document.getElementById('banknameError').innerText = 'Invalid bank name format';
                isValid = false;
            } else {
                document.getElementById('banknameError').innerText = '';
            }

            // Account Holder Name Validation
            let accountholdername = document.getElementById('accountholdername').value;
            if (!accountholdername.match(/^[A-Za-z ]{2,50}$/)) {
                document.getElementById('accountholdernameError').innerText = 'Invalid account holder name format';
                isValid = false;
            } else {
                document.getElementById('accountholdernameError').innerText = '';
            }

            // IFSC Code Validation
            let ifsc = document.getElementById('ifsc').value;
            if (!ifsc.match(/^[A-Za-z]{4}[0-9]{7}$/)) {
                document.getElementById('ifscError').innerText = 'Invalid IFSC code format';
                isValid = false;
            } else {
                document.getElementById('ifscError').innerText = '';
            }

            // Account Type Validation
            let accounttype = document.getElementById('accounttype').value;
            if (accounttype === '') {
                document.getElementById('accounttypeError').innerText = 'Please select an account type';
                isValid = false;
            } else {
                document.getElementById('accounttypeError').innerText = '';
            }

            if (!isValid) {
                event.preventDefault();
            }
        });
    </script>
</body>
</html>
