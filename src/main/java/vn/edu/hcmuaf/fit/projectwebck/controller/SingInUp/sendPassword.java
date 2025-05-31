package vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.EmailVerificationToken;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.EmailVerificationTokenServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@WebServlet(name = "sendPassword", value = "/sendPassword")
public class sendPassword extends HttpServlet {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        String email = request.getParameter("email");
        String userIdStr = request.getParameter("userId");

        // Kiểm tra token và email nếu cần (nếu bạn dùng xác minh)


        if (token == null || email == null || userIdStr == null) {
            request.setAttribute("error", "Yêu cầu không hợp lệ.");
            request.getRequestDispatcher("jsp/SendPassword.jsp").forward(request, response);
            return;
        }

        // Kiểm tra token hợp lệ với email và userId trong DB
        EmailVerificationTokenServices tokenService = new EmailVerificationTokenServices();
        EmailVerificationToken verificationToken = tokenService.findToken(token);
        if (verificationToken == null || !verificationToken.getEmail().equals(email) /* || token hết hạn */) {
            request.setAttribute("error", "Token không hợp lệ hoặc đã hết hạn.");
            request.getRequestDispatcher("/jsp/PasswordBack.jsp").forward(request, response);
            return;
        }

        UserServices us = new UserServices();
        User user = us.findUserByEmailAndId(email, userIdStr);
        if (user == null) {
            request.setAttribute("error", "Không tìm thấy người dùng.");
            request.getRequestDispatcher("/jsp/PasswordBack.jsp").forward(request, response);
            return;
        }

        request.setAttribute("userId", user.getId());
        request.setAttribute("email", email);
        request.setAttribute("token", token);

        request.getRequestDispatcher("jsp/SendPassword.jsp").forward(request, response);
//        if (user != null /* && token hợp lệ nếu bạn dùng token */) {
//            // Gửi userId để form hidden field sử dụng
//            request.setAttribute("userId", user.getId());
//            request.getRequestDispatcher("jsp/SendPassword.jsp").forward(request, response);
//        } else {
//            request.setAttribute("error", "Yêu cầu không hợp lệ.");
//            request.getRequestDispatcher("passwordBack").forward(request, response);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        String token = request.getParameter("token");
        String email = request.getParameter("email");
        String userIdStr = request.getParameter("userId");

        EmailVerificationTokenServices tokenService = new EmailVerificationTokenServices();
        EmailVerificationToken verificationToken = tokenService.findToken(token);

        if (verificationToken == null || !verificationToken.getEmail().equals(email) /* || token hết hạn */) {
            request.setAttribute("error", "Token không hợp lệ hoặc đã hết hạn.");
            request.getRequestDispatcher("/jsp/SendPassword.jsp").forward(request, response);
            return;
        }

        if( newPassword == null || confirmPassword == null || newPassword.length() < 6 || confirmPassword.length() < 6 ) {
            request.setAttribute("error", "Mat khau phai co it nhat 6 ki tu");
            request.getRequestDispatcher("/jsp/SendPassword.jsp").forward(request,response);
            return;
        }

        if(!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Mat khau khong khop");
            request.getRequestDispatcher("/jsp/SendPassword.jsp").forward(request,response);
            return;
        }

        try{
            if (userIdStr == null || userIdStr.isEmpty()) {
                request.setAttribute("error", "Khong tim thay thong tin nguoi dung.");
                request.getRequestDispatcher("/jsp/SendPassword.jsp").forward(request, response);
                return;
            }

            int userId = Integer.parseInt(userIdStr);

            UserServices userService = new UserServices();
            User user = userService.findUserByEmailAndId(email, userIdStr);

            if (user == null) {
                request.setAttribute("error", "Không tìm thấy người dùng với ID này.");
                request.getRequestDispatcher("/jsp/SendPassword.jsp").forward(request, response);
                return;
            }

            byte[] salt = generateSalt();
            String saltBase64 = Base64.getEncoder().encodeToString(salt);
            String hashedPassword = hashPassword(newPassword, salt);

            user.setPassword(hashedPassword);
            user.setSalt(saltBase64);
            user.setId(userId);


            userService.updatePasswordWithSalt(user);
            tokenService.markAsUsed(email, token);
            request.setAttribute("message", "Doi mat khau thanh cong");
            response.sendRedirect("showLogin");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Da xay ra loi. Vui long thu lai sau.");
            request.getRequestDispatcher("/jsp/SendPassword.jsp").forward(request, response);
        }


    }


    private  String hashPassword(String password, byte[] salt) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Loi khi hash mat khau", e);
        }
    }

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}