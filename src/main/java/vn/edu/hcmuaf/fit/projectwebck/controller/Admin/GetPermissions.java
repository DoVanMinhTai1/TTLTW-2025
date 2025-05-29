package vn.edu.hcmuaf.fit.projectwebck.controller.Admin;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Role;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getPermissions", value = "/getPermissions")
public class GetPermissions extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (session == null || session.getAttribute("user") == null) {
            out.print("[]");
            out.flush();
            return;
        }

        User user = (User) session.getAttribute("user");
        Role role = Role.fromId(user.getRole());
        out.print(new Gson().toJson(role.getPermissions()));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}