<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Bank</title>
</head>
<body>
    <h1>Add a New Bank</h1>
    <form action="/add-bank" method="POST">
        <label for="bankName">Bank Name:</label>
        <input type="text" id="bankName" name="bankName" required>
        <br><br>
        <label for="bankCode">Bank Code:</label>
        <input type="text" id="bankCode" name="bankCode" required>
        <br><br>
        <button type="submit">Add Bank</button>
    </form>
</body>
</html>
