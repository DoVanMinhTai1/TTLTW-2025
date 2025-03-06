package vn.edu.hcmuaf.fit.projectwebck.controller.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "UpdatePassword", value = "/updatePassword")
public class UpdatePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServices us = new UserServices();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            request.setAttribute("error", "Người dùng không tồn tại.");
            request.getRequestDispatcher("Customer.jsp?runScript=option3").forward(request, response);
            return;
        }
        // Lấy thông tin từ form
        String oldPassword = request.getParameter("oldpassword");
        String newPassword = request.getParameter("newpassword");
        String confirmPassword = request.getParameter("confirm-password");

        // Kiểm tra các điều kiện nhập vào
        if (oldPassword == null || newPassword == null || confirmPassword == null) {
            request.setAttribute("error", "Tất cả các trường đều phải được điền đầy đủ.");
            request.getRequestDispatcher("Customer.jsp?runScript=option3").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu cũ
        UserServices userServices = new UserServices();
        User user = userServices.getUserById(currentUser.getId()); // Hoặc lấy ID từ session nếu cần
        if (user == null) {
            request.setAttribute("error", "Người dùng không tồn tại.");
            request.getRequestDispatcher("Customer.jsp?runScript=option3").forward(request, response);
            return;
        }

        String storedPassword = user.getPassword();
        String hashedOldPassword = hashPassword(oldPassword);

        if (!hashedOldPassword.equals(storedPassword)) {
            request.setAttribute("error", "Mật khẩu cũ không đúng.");
            request.getRequestDispatcher("Customer.jsp?runScript=option3").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu mới và xác nhận
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu mới và xác nhận không khớp.");
            request.getRequestDispatcher("Customer.jsp?runScript=option3").forward(request, response);
            return;
        }

        // Cập nhật mật khẩu mới
        user.setPassword(hashPassword(newPassword)); // Mã hóa mật khẩu mới
        userServices.updatePassword(user);

        // Thông báo thành công
        request.setAttribute("success", "Mật khẩu đã được thay đổi thành công!");
        request.getRequestDispatcher("Customer.jsp?runScript=option3").forward(request, response);
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