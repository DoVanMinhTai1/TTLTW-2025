package vn.edu.hcmuaf.fit.projectwebck.controller.Home.Vegetables;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "showVegetables", value = "/showVegetables")
public class showVegetables extends  HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServices productService = new ProductServices();
        List<Product> vegetables = productService.getAllVegetables();

        if (request.getAttribute("listVegetables") != null) {
            vegetables = (List<Product>) request.getAttribute("listVegetables");
        } else {
            vegetables = productService.getAllVegetables(); // Lấy danh sách sản phẩm từ database
        }

        int count = productService.getTotalVegetables();
        int productsPerPage = 50; // Số sản phẩm trên mỗi trang
        int endPage = (int) Math.ceil((double) count / productsPerPage);

        request.setAttribute("endPage", endPage);

        request.setAttribute("listVegetables",vegetables);
        request.getRequestDispatcher("jsp/Vegetables.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
