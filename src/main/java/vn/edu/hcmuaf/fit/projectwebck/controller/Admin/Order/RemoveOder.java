package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.LogsServices;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveOder", value = "/removeOder")
public class RemoveOder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("oid");
        int oid = Integer.parseInt(id);
        OrderServices service = new OrderServices();
        Order orderRm=service.getOrderById(oid);
        service.removeOrder(oid);
        List<Order> listOrder = service.getAllOrders();
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserServices userService = new UserServices();
            User user = (User) session.getAttribute("user"); ;
            if (user != null) {
                // Gọi LogService để ghi log
                LogsServices logService = new LogsServices();
                logService.danger(user.getUsername()+" đã xóa một đơn hàng",user.getId(),"Xóa đơn hàng",orderRm.toString(),"");
            }
        }
        request.setAttribute("listpromotion", listOrder);
        request.setAttribute("message", "Xóa đơn hàng thành công");
        request.getRequestDispatcher("Admin.jsp?runScript=option4").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}