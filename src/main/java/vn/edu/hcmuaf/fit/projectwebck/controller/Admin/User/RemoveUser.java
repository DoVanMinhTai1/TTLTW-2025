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
        request.setAttribute("message", "Xóa user thành công");
        request.getRequestDispatcher("Admin.jsp?runScript=option3").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}