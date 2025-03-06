package vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;

@WebServlet(name = "SendPasswordServlet", value = "/sendPassword")
public class sendPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        UserServices us = new UserServices();
        // Xác thực số điện thoại
        if (phone == null || phone.isEmpty()) {
            setMessageAndForward(request, response, "Số điện thoại không hợp lệ.");
            return;
        }

        String dbMessage = us.checkPhoneInDatabase(phone.trim());
        if (dbMessage != null) {
            setMessageAndForward(request, response, dbMessage);
            return;
        }

        String message = sendPasswordViaSMS(phone.trim());
        // Logic gửi mật khẩu qua SMS (giả lập ở đây)
//        String message = "Mật khẩu đã được gửi đến số điện thoại " + phone;

        // Gửi thông báo tới trang JSP
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/PasswordBack.jsp");
        dispatcher.forward(request, response);
    }

    private void setMessageAndForward(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/PasswordBack.jsp");
        dispatcher.forward(request, response);
    }

    private String sendPasswordViaSMS(String phone) {
        // Giả lập gửi mật khẩu. Thực tế, bạn nên sử dụng một dịch vụ SMS.
        // Gửi mật khẩu và trả về thông báo thông báo
        // Ví dụ: "Mật khẩu đã được gửi đến số điện thoại +84123456789"
        return "Mật khẩu đã được gửi đến số điện thoại " + phone;
    }
}