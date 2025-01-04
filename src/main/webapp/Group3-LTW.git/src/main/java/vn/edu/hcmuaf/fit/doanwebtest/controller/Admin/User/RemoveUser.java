package vn.edu.hcmuaf.fit.doanwebtest.controller.Admin.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.User;
import vn.edu.hcmuaf.fit.doanwebtest.services.UserServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveUser", value = "/removeUser")
public class RemoveUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("uid");
        int uid = Integer.parseInt(id);
        UserServices userServices = new UserServices();
        userServices.removeUser(uid);
        List<User> listUser = userServices.getAllUsers();
        request.setAttribute("listuser",listUser);
        request.getRequestDispatcher("Admin.jsp?runScript=option3").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}