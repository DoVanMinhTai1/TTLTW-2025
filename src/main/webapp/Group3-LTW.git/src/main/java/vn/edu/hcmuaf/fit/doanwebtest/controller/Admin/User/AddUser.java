package vn.edu.hcmuaf.fit.doanwebtest.controller.Admin.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Product;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.User;
import vn.edu.hcmuaf.fit.doanwebtest.services.UserServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddUser", value = "/addUser")
public class AddUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("FullName");
        String phone = request.getParameter("Phone");
        String email = request.getParameter("Email");
        String username = request.getParameter("UserName");
        String password = request.getParameter("Password");
        String roleStr = request.getParameter("Role");
        String birthday = request.getParameter("Birthday");

        if (roleStr.equals("Quản trị viên")) {
            roleStr = "1";
        } else {
            roleStr = "0";
        }
        int role = Integer.parseInt(roleStr);

        User user = new User();
        user.setFullName(fullName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setDateOfBirth(birthday);

        UserServices userServices = new UserServices();
        userServices.insertUser(user);
        List<User> listUser = userServices.getAllUsers();
        request.setAttribute("listuser",listUser);
        request.getRequestDispatcher("Admin.jsp?runScript=option3").forward(request,response);
    }

}