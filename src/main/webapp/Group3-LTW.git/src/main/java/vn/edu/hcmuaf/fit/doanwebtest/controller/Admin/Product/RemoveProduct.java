package vn.edu.hcmuaf.fit.doanwebtest.controller.Admin.Product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Product;
import vn.edu.hcmuaf.fit.doanwebtest.services.ProductServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveProduct", value = "/removeProduct")
public class RemoveProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("pid");
        int pid = Integer.parseInt(id);
        ProductServices productService = new ProductServices();
        productService.removeProduct(pid);
        List<Product> products = productService.getAll();
        request.setAttribute("listproduct", products);
        request.getRequestDispatcher("Admin.jsp?runScript=option2").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}