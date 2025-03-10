<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style>
        form {
            width: 300px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
            text-align: center;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="date"] {
            width: 90%;
            padding: 6px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 15px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error {
            color: red;
            font-size: 14px;
        }
    </style>

    <script>
        function validateForm() {
            let firstDate = document.getElementById("firstdate").value;
            let lastDate = document.getElementById("lastdate").value;
            let errorMessage = "";

            if (!firstDate) {
                errorMessage = "Please select a start date.";
            } else if (!lastDate) {
                errorMessage = "Please select an end date.";
            } else if (new Date(lastDate) < new Date(firstDate)) {
                errorMessage = "End date cannot be before the start date.";
            }

            if (errorMessage) {
                document.getElementById("error-message").innerText = errorMessage;
                return false;
            }
            return true;
        }

        window.onload = function() {
            let today = new Date().toISOString().split("T")[0];
            document.getElementById("firstdate").setAttribute("max", today);
            document.getElementById("lastdate").setAttribute("max", today);
        };
    </script>
</head>
<body>

<form action="downloadtransactionhistoryfile" method="post" onsubmit="return validateForm();">
    <h1 style="text-align: center;">Select Duration</h1>
    <label for="firstdate">First Date:</label>
    <input type="date" id="firstdate" name="firstdate">
    <br>
    <label for="lastdate">Last Date:</label>
    <input type="date" id="lastdate" name="lastdate">
    <br>
    <span id="error-message" class="error"></span>
    <br>
    <div class="submit">
        <input type="submit" value="Download">
    </div>
</form>

</body>
</html>
