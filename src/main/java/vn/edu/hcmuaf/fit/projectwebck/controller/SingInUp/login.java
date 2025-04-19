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

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userCaptcha = request.getParameter("captcha");
//        User user = userService.login(username, password);
        String captchaSession = (String) session.getAttribute("captcha");
        // Kiểm tra CAPTCHA trước khi xử lý đăng nhập
        if (captchaSession == null || !captchaSession.equals(userCaptcha)) {
            request.setAttribute("errorMessage", "CAPTCHA không chính xác.");
            request.getRequestDispatcher("jsp/SignInUp.jsp").forward(request, response);
            return; // Dừng xử lý nếu CAPTCHA sai
        }
        UserServices userService = new UserServices();
        User user = userService.findUserByUsername(username);
        if (user != null) {
            String hashedPassword = hashPassword(password);
            if (hashedPassword.equals(user.getPassword())) {
                session.setAttribute("user", user);
                if (user.getRole() == 1 || user.getRole() == 2) {
                    response.sendRedirect("showAdmin");
                } else {
                    response.sendRedirect("showHome");
                }
            }
            else {
                request.setAttribute("errorMessage", "Tên người dùng hoặc mật khẩu không chính xác.");
                request.getRequestDispatcher("jsp/SignInUp.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Tên người dùng hoặc mật khẩu không chính xác.");
            request.getRequestDispatcher("jsp/SignInUp.jsp").forward(request, response);
        }

    }
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