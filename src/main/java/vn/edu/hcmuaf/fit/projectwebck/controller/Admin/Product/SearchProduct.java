package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchProduct", value = "/searchProduct")
public class SearchProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("name"); // Lấy từ khóa tìm kiếm từ request
        ProductServices productServices = new ProductServices();

        // Tìm kiếm sản phẩm theo tên
        List<Product> searchedProducts = productServices.searchByName(keyword);

        // Đặt danh sách vào request và chuyển đến trang JSP
        request.setAttribute("listproduct", searchedProducts);

        request.getRequestDispatcher("Admin.jsp?runScript=option2").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}