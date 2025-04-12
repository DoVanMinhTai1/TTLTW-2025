package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.LogsServices;
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
        User userRm= userServices.getUserById(uid);
        userServices.removeUser(uid);
        List<User> listUser = userServices.getAllUsers();
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserServices userService = new UserServices();
            User user = (User) session.getAttribute("user"); ;
            if (user != null) {
                // Gọi LogService để ghi log
                LogsServices logService = new LogsServices();
                logService.danger(user.getUsername()+" đã xóa một tài khoản",user.getId(),"Xóa tài khoản",userRm.toString(),"");
            }
        }
        request.setAttribute("listuser",listUser);
        request.setAttribute("message", "Xóa user thành công");
        request.getRequestDispatcher("Admin.jsp?runScript=option3").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}