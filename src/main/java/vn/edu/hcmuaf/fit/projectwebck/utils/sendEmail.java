package vn.edu.hcmuaf.fit.projectwebck.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class sendEmail {
    private Session session;

    public sendEmail() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.email.eu-frankfurt-1.oci.oraclecloud.com");
        props.put("mail.smtp.port", "584");
        props.put("mail.from", "...@email.com");
        props.put("mail.smtp.auth", "true");
        String username = "abc";
        String password = "pass";

        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void sendEmail(String to, String subject, String body) {
        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to)});
            message.setSubject(subject);
            message.setContent("content", "text/plain");

            Transport.send(message);
        } catch (Exception e) {
            throw new IllegalStateException("Error sending email", e);
        }
    }
}
