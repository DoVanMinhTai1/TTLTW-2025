package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.LocalDateTimeAdapter;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "SearchProduct", value = "/searchProduct")
public class SearchProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        ProductServices productServices = new ProductServices();
        List<Product> searchedProducts;
        if (keyword != null && !keyword.isEmpty()) {
            searchedProducts = productServices.searchByName(keyword);
        } else {
            searchedProducts = productServices.getAll();
        }

        // Trả về JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Dùng thư viện Gson để convert list -> JSON
        com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())  // Đăng ký adapter nếu có LocalDateTime
                .create();

        // Chuyển đối tượng thành JSON và gửi về client
        String json = gson.toJson(searchedProducts);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}