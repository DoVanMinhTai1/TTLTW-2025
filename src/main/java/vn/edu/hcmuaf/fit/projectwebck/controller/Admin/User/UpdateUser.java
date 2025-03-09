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

@WebServlet(name = "UpdateUser", value = "/updateUser")
public class UpdateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("uid"));
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

        User userUpdate = new User();
        userUpdate.setId(id);
        userUpdate.setFullName(fullName);
        userUpdate.setPhone(phone);
        userUpdate.setEmail(email);
        userUpdate.setUsername(username);
        userUpdate.setPassword(password);
        userUpdate.setRole(role);
        userUpdate.setDateOfBirth(birthday);

        UserServices userServices = new UserServices();
        userServices.updateUser(userUpdate);
        List<User> listUser = userServices.getAllUsers();
        request.setAttribute("listuser",listUser);
        request.setAttribute("message", "Cập nhật user thành công");
        request.getRequestDispatcher("Admin.jsp?runScript=option3").forward(request,response);

    }
}