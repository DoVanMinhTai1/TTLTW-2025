package vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServices userService = new UserServices();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        User user = userService.login(username, password);
        User user = userService.findUserByUsername(username);

        if (user != null) {
            String hashedPassword = hashPassword(password);
            if (hashedPassword.equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                if (user.getRole() == 1) {
                    response.sendRedirect("showAdmin");
                } else {
                    response.sendRedirect("showHome");
                }
            }
            else {
                request.setAttribute("errorMessage", "Tên người dùng hoặc mật khẩu không chính xác.");
                response.sendRedirect("showLogin");
            }
        } else {
            request.setAttribute("errorMessage", "Tên người dùng hoặc mật khẩu không chính xác.");
            response.sendRedirect("showLogin");
        }

    }



//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        UserServices userService = new UserServices();
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        User user = userService.findUserByUsername(username);
//
//        if (user != null) {
//            // Băm mật khẩu nhập vào
//            String hashedPassword = hashPassword(password);
//
//            // Kiểm tra mật khẩu
//            if (hashedPassword.equals(user.getPassword())) {
//                // Mật khẩu đúng, tạo phiên mới
//                HttpSession session = request.getSession();
//                session.setAttribute("user", user);
////                session.setMaxInactiveInterval(30 * 60); // Thời gian hết hạn phiên (30 phút)
//
//                // Chuyển hướng dựa trên vai trò của người dùng
//                if (user.getRole() == 0) {
//                    response.sendRedirect("showHome");
//                } else {
//                    response.sendRedirect("showAdmin");
//                }
//            }
//        } else {
//            request.setAttribute("errorMessage", "Tên người dùng hoặc mật khẩu không chính xác.");
//            response.sendRedirect("showLogin");
//        }
//
//    }
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();

            // Chuyển đổi mảng byte thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}