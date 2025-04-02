package com.net.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.net.model.Transactions;
import com.net.services.TransactionHistoryService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class TransactionController {

    @Autowired
    private TransactionHistoryService historyService;

    @RequestMapping("/transactionfile")
    public String transactionPage() {
        return "transactionfile"; // JSP file name
    }

    @PostMapping("/downloadtransactionhistoryfile")
    public void generateTransactionPDF(HttpSession session, HttpServletResponse response,
                                       @RequestParam("firstdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String firstdateStr,
                                       @RequestParam("lastdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String lastdateStr)
            throws IOException {
        Integer customerId = (Integer) session.getAttribute("custID");

        if (customerId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer ID not found");
            return;
        }

        // Convert String to SQL Date
        Date firstdate = Date.valueOf(firstdateStr);
        Date lastdate = Date.valueOf(lastdateStr);

        List<Transactions> transactions = historyService.getTransactionHistoryBasedOnCustId(customerId, firstdate, lastdate);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Transaction_History.pdf");

        try (java.io.OutputStream out = response.getOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("BOT Bank Transaction History", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            document.add(title);

            // Table
            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setSpacingAfter(10);

            String[] headers = { "Transaction ID", "Date", "Time", "Type", "Account Number", "Amount", "Balance", "Status" };
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            if (transactions != null && !transactions.isEmpty()) {
                for (Transactions t : transactions) {
                    table.addCell(String.valueOf(t.getTransactionid()));
                    table.addCell(t.getTransactiondate() != null ? t.getTransactiondate().toString() : "N/A");
                    table.addCell(t.getTransactiontime() != null ? t.getTransactiontime().toString() : "N/A");
                    table.addCell(t.getTransactiontype());
                    table.addCell(t.getAccountnumber());
                    table.addCell(String.valueOf(t.getProcessingamount()));
                    table.addCell(t.getBalance() != null ? String.valueOf(t.getBalance()) : "N/A");
                    table.addCell(t.getStatus());
                }
            } else {
                PdfPCell noDataCell = new PdfPCell(new Phrase("No transactions found", new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC)));
                noDataCell.setColspan(8);
                noDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(noDataCell);
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new IOException("Error generating PDF", e);
        }
    }
}