package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;

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