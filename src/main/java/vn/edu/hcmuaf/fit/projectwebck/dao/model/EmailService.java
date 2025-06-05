package vn.edu.hcmuaf.fit.projectwebck.dao.model;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Transport;

public class EmailService {
    private static Session session;

    static {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("22130238@st.hcmuaf.edu.vn", "zobi vtfr drzd nlgg");
            }
        });
    }

    /**
     * Kiểm tra định dạng email hợp lệ
     */
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }

    /**
     * Gửi email cập nhật trạng thái đơn hàng
     */
    public static void sendOrderStatusEmail(String toEmail, String customerName, int orderCode, String newStatus) {
        if (!isValidEmail(toEmail)) {
            System.out.println("Email không hợp lệ: " + toEmail);
            return;
        }
        System.out.println("Gửi email cho: " + toEmail);
        System.out.println("Trạng thái đơn hàng: " + newStatus);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("22130332@st.hcmuaf.edu.vn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Cập nhật trạng thái đơn hàng #" + orderCode);

            String html = "<h3>Xin chào " + customerName + ",</h3>" +
                    "<p>Trạng thái đơn hàng <strong>#" + orderCode + "</strong> của bạn đã được cập nhật:</p>" +
                    "<p><strong>Trạng thái mới:</strong> " + newStatus + "</p>" +
                    "<p>Cảm ơn bạn đã mua hàng tại cửa hàng của chúng tôi!</p>";

            message.setContent(html, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Email đã được gửi thành công đến " + toEmail);

        } catch (AddressException e) {
            System.out.println("Lỗi định dạng địa chỉ email: " + toEmail);
            e.printStackTrace();
        } catch (MessagingException e) {
            System.out.println("Lỗi khi gửi email:");
            e.printStackTrace();
        }
    }
}