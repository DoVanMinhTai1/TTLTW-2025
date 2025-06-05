package vn.edu.hcmuaf.fit.projectwebck.controller.Home.Vegetables;

import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

@WebServlet(name = "showVegetablesServlet", value = "/showVegetables")
public class showVegetables extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServices productService = new ProductServices();
        List<Product> vegetables = productService.getAllVegetables();

        if (request.getAttribute("listPaging") != null) {
            vegetables = (List<Product>) request.getAttribute("listPaging");
        } else {
            vegetables = productService.getAllVegetables(); // Lấy danh sách sản phẩm từ database
        }

        request.setAttribute("listPaging",vegetables);
        request.getRequestDispatcher("jsp/Vegetables.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}