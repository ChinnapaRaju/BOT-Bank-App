<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List, java.time.LocalDateTime, java.time.format.DateTimeFormatter, com.net.model.Transactions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Transaction History</title>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .print-btn {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            font-size: 16px;
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
            text-align: center;
        }
        .print-btn:hover {
            background-color: #218838;
        }
        @media print {
            .print-btn {
                display: none;
            }
        }
    </style>
    <script>
        function printPage() {
            window.print();
        }
    </script>
</head>
<body>

<h2 style="text-align: center;">Transaction History</h2>

<%
    List<Transactions> transactions = (List<Transactions>) request.getAttribute("history");

    if (transactions == null || transactions.isEmpty()) {
%>
    <p style="text-align: center; color: red;">No transactions found.</p>
<%
    } else {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
%>

<table>
    <tr>
        <th>Transaction ID</th>
        <th>Date & Time</th>
        <th>Type</th>
        <th>Account Number</th>
        <th>Amount</th>
        <th>Balance</th>
        <th>Status</th>
    </tr>
    <%
        for (Transactions t : transactions) {
            LocalDateTime txnDate = t.getTransactiondatetime();
            String formattedDate = (txnDate != null) ? "D: " + txnDate.format(dateFormatter) + " T: " + txnDate.format(timeFormatter) : "N/A";
    %>
    <tr>
        <td><%= t.getTransactionid() %></td>
        <td><%= formattedDate %></td> 
        <td><%= t.getTransactiontype() %></td>
        <td><%= t.getAccountnumber() %></td>
        <td><%= t.getProcessingamount() %></td>
        <td><%= t.getBalance() != null ? t.getBalance() : "N/A" %></td>
        <td><%= t.getStatus() %></td>
    </tr>
    <%
        }
    %>
</table>

<!-- Print button moved below the table -->
<button class="print-btn" onclick="printPage()">Print</button>

<%
    }
%>

</body>
</html>
