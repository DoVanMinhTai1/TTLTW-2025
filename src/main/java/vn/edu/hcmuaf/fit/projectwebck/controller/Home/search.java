package vn.edu.hcmuaf.fit.projectwebck.controller.Home;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "search", value = "/search")
public class search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("search"); // Lấy từ khóa tìm kiếm từ request
        ProductServices productServices = new ProductServices();

        // Tìm kiếm sản phẩm theo tên
        List<Product> searchedProducts = productServices.searchByName(keyword);

        // Đặt danh sách vào request và chuyển đến trang JSP
        request.setAttribute("listproduct", searchedProducts);

        request.getRequestDispatcher("jsp/Search.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}