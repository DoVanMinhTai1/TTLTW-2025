package vn.edu.hcmuaf.fit.projectwebck.controller.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;

@WebServlet(name = "UpdatePassword", value = "/updatePassword")
public class UpdatePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        User user = userServices.getUserById(1);
        String storedPassword = user.getPassword();


        if (!oldPassword.equals(storedPassword)) {
            request.setAttribute("error", "Mật khẩu cũ không đúng.");
            request.getRequestDispatcher("Customer.jsp?runScript=option3").forward(request, response);
            return;
        }
        user.setPassword(newPassword);
        userServices.updatePassword(user);

        // Thông báo thành công
        request.setAttribute("success", "Mật khẩu đã được thay đổi thành công!");
        request.getRequestDispatcher("Customer.jsp?runScript=option3").forward(request, response);
    }
}