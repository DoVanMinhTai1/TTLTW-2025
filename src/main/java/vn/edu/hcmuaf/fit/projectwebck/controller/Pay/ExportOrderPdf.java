package vn.edu.hcmuaf.fit.projectwebck.controller.Pay;

import java.io.*;

import com.google.gson.JsonObject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;

@WebServlet(name = "exportPdf", value = "/exportPdf")
public class ExportOrderPdf extends HttpServlet {
    private String message;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=orders.pdf");
        BufferedReader bf = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        JsonObject jsonObject = com.google.gson.JsonParser.parseString(sb.toString()).getAsJsonObject();

        int orderId = jsonObject.get("orderId").getAsInt();

        OrderServices orderServices = new OrderServices();
        Order order = orderServices.getOrderById(orderId);

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            String fontPath = getServletContext().getRealPath("/arial-font/arial.ttf"); // ensure this exists
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(baseFont, 18, Font.BOLD);
            Font cellFont = new Font(baseFont, 10);

            // Add centered title using table
            PdfPTable titleTable = new PdfPTable(1);
            titleTable.setWidthPercentage(100f);
            PdfPCell titleCell = new PdfPCell(new Phrase("Đơn Hàng", titleFont));
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setBorder(Rectangle.NO_BORDER);
            titleCell.setPaddingBottom(10f);
            titleTable.addCell(titleCell);
            document.add(titleTable);

            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            table.setWidths(new float[]{2f, 5f}); // wider value column

            // Add order details
            table.addCell(new Phrase("Order ID"));
            table.addCell(new Phrase(String.valueOf(order.getId())));

            table.addCell(new Phrase("User ID"));
            table.addCell(new Phrase(String.valueOf(order.getUserId())));

            table.addCell(new Phrase("Booking Date"));
            table.addCell(new Phrase(order.getDateOfBooking()));

            table.addCell(new Phrase("Money"));
            table.addCell(new Phrase(String.valueOf(order.getMoney())));

            table.addCell(new Phrase("Status"));
            table.addCell(new Phrase(String.valueOf(order.getStatus())));

            table.addCell(new Phrase("Address ID"));
            table.addCell(new Phrase(String.valueOf(order.getAddressId())));

            table.addCell(new Phrase("Full Name"));
            table.addCell(new Phrase(order.getFullName()));

            table.addCell(new Phrase("Phone"));
            table.addCell(new Phrase(order.getPhone()));

            table.addCell(new Phrase("Address"));
            table.addCell(new Phrase(order.getAddress()));

            table.addCell(new Phrase("Third Party ID"));
            table.addCell(new Phrase(order.getThirty_party_id()));
            document.add(table);

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        document.close();


    }
}