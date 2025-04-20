package vn.edu.hcmuaf.fit.projectwebck.controller.Home;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "search", value = "/search")
public class search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ajaxRequest = request.getParameter("ajax");
        if ("true".equals(ajaxRequest)) {
            doAjaxSearch(request, response); // Gọi phương thức xử lý AJAX
        } else {
            // Xử lý yêu cầu không phải AJAX
            String keyword = request.getParameter("search");
            ProductServices productServices = new ProductServices();
            List<Product> searchedProducts = productServices.searchByName(keyword);
            request.setAttribute("listproduct", searchedProducts);
            request.getRequestDispatcher("jsp/Search.jsp").forward(request, response);
        }
    }

    private void doAjaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("search");
        ProductServices productServices = new ProductServices();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            List<Product> results;
            if (keyword == null || keyword.trim().isEmpty()) {
                results = Collections.emptyList();
            } else {
                // Thêm logic bảo vệ chống SQL injection
                String safeKeyword = keyword.replace("%", "\\%")
                        .replace("_", "\\_");
                results = productServices.searchByName(safeKeyword);
            }

            new ObjectMapper().writeValue(response.getWriter(), results);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("[]");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}