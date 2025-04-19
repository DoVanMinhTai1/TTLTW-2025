package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchOrder", value = "/searchOrder")
public class SearchOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword") ; // Lấy từ khóa tìm kiếm từ request
        OrderServices service = new OrderServices();

        List<Order> orders;
        if (keyword != null && !keyword.isEmpty()) {
            try {
                int id = Integer.parseInt(keyword);
                orders = service.searchById(id);
            } catch (NumberFormatException e) {
                orders = new ArrayList<>(); // keyword không hợp lệ -> rỗng
            }
        } else {
            orders = service.getAllOrders();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        response.getWriter().write(gson.toJson(orders));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}