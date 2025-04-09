package vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.EmailVerificationToken;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.EmailVerificationTokenServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;

@WebServlet(name = "register", value = "/register")
public class register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        String email = request.getParameter("email");
        EmailVerificationTokenServices emailVerificationTokenServices = new EmailVerificationTokenServices();

        // Kiểm tra token và email
        boolean isVerified = emailVerificationTokenServices.verifyToken(email, token);

        if (isVerified) {
            // Đánh dấu token là đã sử dụng
            emailVerificationTokenServices.markAsUsed(email, token);

            // Thiết lập thông báo thành công
            request.setAttribute("message", "Bạn đã xác nhận tài khoản thành công!");
        } else {
            request.setAttribute("message", "Xác nhận tài khoản không thành công. Vui lòng kiểm tra lại.");
        }

        // Chuyển hướng đến trang login (showLogin)
        request.getRequestDispatcher("showLogin").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServices us = new UserServices();
        EmailVerificationTokenServices emailVerificationTokenServices = new EmailVerificationTokenServices();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        if(username== null || password==null || phone==null || email==null){
            request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin.");
            request.getRequestDispatcher("jsp/SignInUp.jsp").forward(request, response);
            return;
        }
        if (us.isUsernameTaken(username)) {
            request.setAttribute("errorMessage", "Tên người dùng đã tồn tại.");
            request.getRequestDispatcher("jsp/SignInUp.jsp").forward(request, response);
            return;
        }

        String hashedPassword = hashPassword(password);
        
        User user = new User();
        user.setUsername(username);
        user.setFullName(username);
        user.setPassword(hashedPassword);
        user.setPhone(phone);
        user.setEmail(email);

        int num = us.register(user);
        if(num>0){
            String token = generateToken(16);

            EmailVerificationToken verificationToken = new EmailVerificationToken(email, token);
            System.out.println("Email: " + verificationToken.getEmail());
            System.out.println("Token: " + verificationToken.getToken());
            emailVerificationTokenServices.insertToken(verificationToken);

            // Tạo link xác nhận
            String contextPath = request.getContextPath();
            String verificationLink = "http://localhost:8080" + contextPath + "/register?token=" + token + "&email=" + email;
            // Gửi email
            sendEmail(email, verificationLink);

            response.getWriter().write("Email xác nhận tài khoản đã được gửi tới email của bạn.");
            response.sendRedirect("showLogin");
        } else {
            response.getWriter().write("Đăng ký không thành công. Vui lòng thử lại!");
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

    private static String generateToken(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[length];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    private void sendEmail(String email, String verificationLink) {
        String from = "22130180@st.hcmuaf.edu.vn";
        String password = "mlir vshn tbhc wpml";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Xác Nhận Địa Chỉ Email");
            message.setText("Vui lòng nhấp vào liên kết sau để xác nhận địa chỉ email của bạn:\n" + verificationLink);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}