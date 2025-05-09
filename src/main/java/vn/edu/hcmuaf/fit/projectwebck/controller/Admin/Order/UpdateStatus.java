package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;

import java.io.IOException;

@WebServlet(name = "UpdateStatus", value = "/UpdateStatus")
public class UpdateStatus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int status = Integer.parseInt(request.getParameter("status"));
            OrderServices service = new OrderServices();
            boolean updated =  service.updateOrderStatus(orderId, status);

            response.setContentType("text/plain");
            if (updated) {
                response.getWriter().write("OK");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Cập nhật không thành công.");
            }

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Dữ liệu không hợp lệ.");
        }
    }
}