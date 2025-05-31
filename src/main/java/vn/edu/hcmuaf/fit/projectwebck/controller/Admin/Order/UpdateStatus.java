package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.EmailService;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

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
            String statusText;

            switch (status) {
                case 0:
                    statusText = "Chờ xác nhận";
                    break;
                case 1:
                    statusText = "Đã xác nhận";
                    break;
                case 2:
                    statusText = "Đang đóng gói";
                    break;
                case 3:
                    statusText = "Đang vận chuyển";
                    break;
                case 4:
                    statusText = "Hoàn tất";
                    break;
                case 5:
                    statusText = "Đã hủy";
                    break;
                default:
                    statusText = "Không xác định";
                    break;
            }
            OrderServices service = new OrderServices();
            boolean updated =  service.updateOrderStatus(orderId, status);
            Order order = service.getOrderById(orderId);
            UserServices user = new UserServices();
            User userCustomer = user.getUserById(order.getUserId());
            EmailService.sendOrderStatusEmail(
                    userCustomer.getEmail(),
                    userCustomer.getFullName(),
                    order.getId(),
                    statusText
            );
            if(updated && status == 1) {
                service.reduceStockWhenOrderConfirmed(orderId);
            }
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