package vn.edu.hcmuaf.fit.projectwebck.controller.Home.Fruits;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "showFruits", value = "/showFruits")
public class showFruits extends  HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServices productService = new ProductServices();
        List<Product> fruits = productService.getAllFruits();

        if (request.getAttribute("listFruits") != null) {
            fruits = (List<Product>) request.getAttribute("listFruits");
        } else {
            fruits = productService.getAllFruits(); // Lấy danh sách sản phẩm từ database
        }

        request.setAttribute("listFruits",fruits);
        request.getRequestDispatcher("jsp/Fruits.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
