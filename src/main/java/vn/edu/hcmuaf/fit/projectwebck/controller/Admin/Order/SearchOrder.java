package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchOrder", value = "/searchOrder")
public class SearchOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int keyword = Integer.parseInt(request.getParameter("searchOrder")) ; // Lấy từ khóa tìm kiếm từ request
        OrderServices service = new OrderServices();

        // Tìm kiếm sản phẩm theo tên
        List<Order> orders=service.searchById(keyword);

        // Đặt danh sách vào request và chuyển đến trang JSP
        request.setAttribute("listorder", orders);

        request.getRequestDispatcher("Admin.jsp?runScript=option4").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}