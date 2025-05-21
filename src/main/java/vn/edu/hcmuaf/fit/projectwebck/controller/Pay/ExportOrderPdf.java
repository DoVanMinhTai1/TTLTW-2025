package vn.edu.hcmuaf.fit.projectwebck.controller.Pay;

import java.io.*;

import com.google.gson.JsonObject;
import com.itextpdf.text.*;
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

            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            document.add(new Paragraph("Đơn Hàng", titleFont));
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(10);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            // Header row
            table.addCell("Order ID");
            table.addCell("User ID");
            table.addCell("Booking Date");
            table.addCell("Money");
            table.addCell("Status");
            table.addCell("Address ID");
            table.addCell("Full Name");
            table.addCell("Phone");
            table.addCell("Address");
            table.addCell("Third Party ID");

            table.addCell(String.valueOf(order.getId()));
            table.addCell(String.valueOf(order.getUserId()));
            table.addCell(order.getDateOfBooking());
            table.addCell(String.valueOf(order.getMoney()));
            table.addCell(String.valueOf(order.getStatus()));
            table.addCell(String.valueOf(order.getAddressId()));
            table.addCell(order.getFullName());
            table.addCell(order.getPhone());
            table.addCell(order.getAddress());
            table.addCell(order.getThirty_party_id());

            document.add(table);

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        document.close();


    }
}