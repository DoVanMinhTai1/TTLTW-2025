package vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CaptchaServlet", value = "/CaptchaServlet")
public class CaptchaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 62;
        int height = 20;
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random random = new Random();

        // Sinh mã CAPTCHA ngẫu nhiên
        StringBuilder captchaText = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            captchaText.append(chars[random.nextInt(chars.length)]);
        }

        // Lưu CAPTCHA vào session để kiểm tra sau này
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captchaText.toString());

        // Tạo hình ảnh CAPTCHA
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();

        // Thiết lập màu nền và font chữ
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLUE);

        // Vẽ chuỗi CAPTCHA
        g.drawString(captchaText.toString(), 10, 15);
        g.dispose();

        // Gửi ảnh về client
        response.setContentType("image/png");
        ImageIO.write(bufferedImage, "png", response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}