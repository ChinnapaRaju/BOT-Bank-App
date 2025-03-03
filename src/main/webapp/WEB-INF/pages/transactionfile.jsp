<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
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

        input::placeholder {
            color: #999;
        }

        .submit {
            text-align: center;
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

            let today = new Date();
            today.setHours(0, 0, 0, 0); // Remove time part
            let formattedToday = today.toISOString().split("T")[0];

            if (!firstDate) {
                errorMessage = "Please select a start date.";
            } else if (!lastDate) {
                errorMessage = "Please select an end date.";
            } else if (new Date(lastDate) < new Date(firstDate)) {
                errorMessage = "End date cannot be before the start date.";
            } else if (new Date(firstDate) > today) {
                errorMessage = "Start date cannot be in the future.";
            } else if (new Date(lastDate) > today) {
                errorMessage = "End date cannot be in the future.";
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
<%
    String firstDate = request.getParameter("firstdate");
    String lastDate = request.getParameter("lastdate");
    String errorMessage = null;
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

    // Set today's date properly (reset time to 00:00:00)
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
    cal.set(java.util.Calendar.MINUTE, 0);
    cal.set(java.util.Calendar.SECOND, 0);
    cal.set(java.util.Calendar.MILLISECOND, 0);
    java.util.Date today = cal.getTime();

    if (firstDate != null && lastDate != null) {
        java.util.Date firstDateValue = sdf.parse(firstDate);
        java.util.Date lastDateValue = sdf.parse(lastDate);

        if (lastDateValue.before(firstDateValue)) {
            errorMessage = "End date cannot be before the start date.";
        } else if (firstDateValue.after(today)) {
            errorMessage = "Start date cannot be in the future.";
        } else if (lastDateValue.after(today)) {
            errorMessage = "End date cannot be in the future.";
        }
    }
%>

<form action="downloadtransactionhistoryfile" method="post" onsubmit="return validateForm();">
    <h1 style="text-align: center;">Select Duration</h1>
    <label for="firstdate">First Date:</label>
    <input type="date" id="firstdate" name="firstdate" max="<%= sdf.format(today) %>">
    <br>
    <label for="lastdate">Last Date:</label>
    <input type="date" id="lastdate" name="lastdate" max="<%= sdf.format(today) %>">
    <br>
    <span id="error-message" class="error"><%= errorMessage != null ? errorMessage : "" %></span>
    <br>
    <div class="submit">
        <input type="submit" value="Download">
    </div>
</form>
</body>
</html>
