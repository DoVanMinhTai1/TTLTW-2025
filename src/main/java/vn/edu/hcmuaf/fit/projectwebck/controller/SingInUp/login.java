package vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;

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
            String saltBase64 = user.getSalt();
            String hashedPassword;
            if (saltBase64 == null || saltBase64.isEmpty()) {
                hashedPassword = hashPassword(password);
            } else {
                byte[] salt = Base64.getDecoder().decode(saltBase64);
                hashedPassword = hashPBKDF2(password, salt);
            }
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
    private String hashPBKDF2(String password, byte[] salt) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi hash PBKDF2", e);
        }
    }
}