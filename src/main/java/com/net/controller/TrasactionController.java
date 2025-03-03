package com.net.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class TrasactionController {  // Fixed spelling

	@RequestMapping("/transactionfile")
	public String transactionPage() {
		return "transactionfile";  // This should match the JSP file name
	}

//	@Autowired
//	private TransactionHistoryService historyService;
//	@PostMapping("/downloadtransactionhistoryfile")
//	public String tHistory(Model model, HttpSession session) {
//		Integer custid = (Integer) session.getAttribute("custID"); // Assuming static customer ID (consider getting from session)
//		List<Transactions> list = historyService.getTransactionHistoryBasedOnCustId(custid);
//		model.addAttribute("history", list);
//		return "transactionhistorypdfreport";
//	}
	
	
	@Autowired
    private TransactionHistoryService historyService;

    @PostMapping("/downloadtransactionhistoryfile")
    public void generateTransactionPDF(HttpSession session, HttpServletResponse response) throws IOException {
        Integer customerId = (Integer) session.getAttribute("custID");

        if (customerId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer ID not found");
            return;
        }

        List<Transactions> transactions = historyService.getTransactionHistoryBasedOnCustId(customerId);

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
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setSpacingAfter(10);
            
            String[] headers = { "Transaction ID", "Date & Time", "Type", "Account Number", "Amount", "Balance", "Status" };
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            if (transactions != null) {
                for (Transactions t : transactions) {
                    table.addCell(String.valueOf(t.getTransactionid()));
                    table.addCell(t.getTransactiondatetime() != null ? t.getTransactiondatetime().toString() : "N/A");
                    table.addCell(t.getTransactiontype());
                    table.addCell(t.getAccountnumber());
                    table.addCell(String.valueOf(t.getProcessingamount()));
                    table.addCell(t.getBalance() != null ? String.valueOf(t.getBalance()) : "N/A");
                    table.addCell(t.getStatus());
                }
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new IOException("Error generating PDF", e);
        }
    }
}