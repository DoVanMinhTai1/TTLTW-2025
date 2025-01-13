package vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;
@WebServlet(name = "logout", value = "/logout") 
public class logout extends HttpServlet { 

@Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if(session!=null){
        session.invalidate();
    }
    response.sendRedirect("showLogin");
}
@Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { } 
}