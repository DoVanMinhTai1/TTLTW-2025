package vn.edu.hcmuaf.fit.projectwebck.controller.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListProduct", value = "/showAdmin")
public class Show extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServices productService = new ProductServices();
        List<Product> products = productService.getAll();
        request.setAttribute("listproduct", products);
        OrderServices orderServices = new OrderServices();
        List<Order> listOrder = orderServices.getAllOrders();
        request.setAttribute("listorder", listOrder);

        request.getRequestDispatcher("Admin.jsp?runScript=option1").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
