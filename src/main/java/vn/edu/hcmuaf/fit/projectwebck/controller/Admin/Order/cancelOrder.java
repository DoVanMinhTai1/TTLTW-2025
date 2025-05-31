package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;

import java.io.IOException;

@WebServlet(name = "cancelOrder", value = "/cancelOrder")
public class cancelOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int status = Integer.parseInt(request.getParameter("status"));
        String option = request.getParameter("option");
        String uIdParam = request.getParameter("uId");
        OrderServices service = new OrderServices();
        service.updateOrderStatus(orderId, status);
        request.setAttribute("option", option);
        request.setAttribute("uIdParam", uIdParam);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/showCustomer");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}