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
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;

@WebServlet(name = "PasswordBackServlet", value = "/passwordBack")
public class passwordBack extends HttpServlet {
    UserServices us = new UserServices();
    EmailVerificationTokenServices emailVerificationTokenServices = new EmailVerificationTokenServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/PasswordBack.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        if(username== null ||  email==null){
            request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin.");
            request.getRequestDispatcher("passwordBack").forward(request, response);
            return;
        }

        UserServices us = new UserServices();

        User user = us.findUserByEmailAndUsername(email, username);

        if (user != null) {
            // Gửi email đổi mật khẩu
            String token = generateToken(16); // random token
            EmailVerificationToken verificationToken = new EmailVerificationToken(email, token);
            System.out.println("Email: " + verificationToken.getEmail());
            System.out.println("Token: " + verificationToken.getToken());
            emailVerificationTokenServices.insertToken(verificationToken);

            // Tạo link xác nhận
            String contextPath = request.getContextPath();
            String verificationLink = "http://localhost:8080" + contextPath + "/sendPassword?token=" + token + "&email=" + email;
            // Gửi email
            sendEmail(email, verificationLink);

            response.getWriter().write("Email thay doi mat khau tai khoan da duoc gui toi email cua ban.");
            response.sendRedirect("showLogin");
        } else {
            response.getWriter().write("Khong thanh cong vui long thu lai!");
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
        String password = "ccry xzoc ghup attf";

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
            message.setSubject("Đổi Mật Khẩu Tài Khoản");
            message.setText("Vui lòng nhấp vào liên kết sau để đổi mật khẩu tài khoản:\n" + verificationLink);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

        // Xác thực số điện thoại
//        if (phone == null || phone.isEmpty()) {
//            setMessageAndForward(request, response, "Số điện thoại không hợp lệ.");
//            return;
//        }
//
//        String dbMessage = us.checkPhoneInDatabase(phone.trim());
//        if (dbMessage != null) {
//            setMessageAndForward(request, response, dbMessage);
//            return;
//        }
//
//        String message = sendPasswordViaSMS(phone.trim());
//        // Logic gửi mật khẩu qua SMS (giả lập ở đây)
////        String message = "Mật khẩu đã được gửi đến số điện thoại " + phone;
//
//        // Gửi thông báo tới trang JSP
//        request.setAttribute("message", message);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/PasswordBack.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    private void setMessageAndForward(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
//        request.setAttribute("message", message);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/PasswordBack.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    private String sendPasswordViaSMS(String phone) {
//        // Giả lập gửi mật khẩu. Thực tế, bạn nên sử dụng một dịch vụ SMS.
//        // Gửi mật khẩu và trả về thông báo thông báo
//        // Ví dụ: "Mật khẩu đã được gửi đến số điện thoại +84123456789"
//        return "Mật khẩu đã được gửi đến số điện thoại " + phone;
//    }
}