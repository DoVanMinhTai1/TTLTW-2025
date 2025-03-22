package vn.edu.hcmuaf.fit.projectwebck.controller.Home.Banner;import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "showAll", value = "/showAll")
public class Banner extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String[] listMainBanner = {
                "/img/banner.png",
                "/img/banner.png",
                "/img/banner.png"
        };

        // Gửi dữ liệu vào JSP
        request.setAttribute("listMainBanner", listMainBanner);

        // Chuyển hướng đến trang JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/showAll");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}