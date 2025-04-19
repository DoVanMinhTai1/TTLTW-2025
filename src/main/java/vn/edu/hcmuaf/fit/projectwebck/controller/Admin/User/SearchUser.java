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
        String keyword = request.getParameter("keyword"); // Lấy từ khóa tìm kiếm từ request
        UserServices us = new UserServices();

        List<User> users;
        if (keyword != null && !keyword.isEmpty()) {
            users = us.searchByName(keyword);
        } else {
            users = us.getAllUsers();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String json = gson.toJson(users);
        response.getWriter().write(json);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}