package vn.edu.hcmuaf.fit.doanwebtest.controller.Admin.Order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Order;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Promotion;
import vn.edu.hcmuaf.fit.doanwebtest.services.OrderServices;
import vn.edu.hcmuaf.fit.doanwebtest.services.PromotionServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveOder", value = "/removeOder")
public class RemoveOder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("oid");
        int oid = Integer.parseInt(id);
        OrderServices service = new OrderServices();
        service.removeOrder(oid);
        List<Order> listOrder = service.getAllOrders();
        request.setAttribute("listpromotion", listOrder);
        request.getRequestDispatcher("Admin.jsp?runScript=option4").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}