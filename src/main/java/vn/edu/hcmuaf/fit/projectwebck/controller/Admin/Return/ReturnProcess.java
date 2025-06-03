package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Return;import java.io.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.ReturnRequestDAO;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;
import vn.edu.hcmuaf.fit.projectwebck.services.StockService;

@WebServlet(name = "ReturnProcessSubmit", value = "/ReturnProcessSubmit")
public class ReturnProcess extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        // Đọc nội dung JSON từ body
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        // Parse JSON (có thể dùng thư viện JSON nào đó, ví dụ: org.json hoặc com.google.gson)
        try {
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(sb.toString(), JsonObject.class);

            int returnId = json.get("returnId").getAsInt();
            String adminResponse = json.get("adminResponse").getAsString();
            String status = json.get("status").getAsString();
            int orderId = json.get("orderId").getAsInt();


            ReturnRequestDAO dao = new ReturnRequestDAO();
            dao.updateAdminResponse(returnId, adminResponse, status);
            OrderServices orderServices = new OrderServices();
            StockService stockService = new StockService();
            if (status.equals("accepted")) {
                Order order = orderServices.getOrderById(orderId);
                orderServices.increateStockWhenOrderReturn(orderId);
                orderServices.updateOrderStatus(orderId,7);
            } else  {
                Order order = orderServices.getOrderById(orderId);
                orderServices.updateOrderStatus(orderId,8);
            }
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"message\":\"Thành công\"}");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"message\":\"Lỗi xử lý server\"}");
        }
    }



    public void destroy() {
    }
}