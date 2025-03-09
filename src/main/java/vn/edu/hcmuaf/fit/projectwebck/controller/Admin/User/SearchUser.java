package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchUser", value = "/searchUser")
public class SearchUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("searchUser"); // Lấy từ khóa tìm kiếm từ request
        UserServices us = new UserServices();

        // Tìm kiếm sản phẩm theo tên
        List<User> users = us.searchByName(keyword);

        // Đặt danh sách vào request và chuyển đến trang JSP
        request.setAttribute("listuser", users);

        request.getRequestDispatcher("Admin.jsp?runScript=option3").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}