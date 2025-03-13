package vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp.Oauth2;

import java.io.*;
import java.util.Map;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp.Oauth2.GoogleLogin.getUserInfo;

@WebServlet(name = "OAuth2CallbackServletServlet", value = "/OAuth2CallbackServlet-servlet")
public class OAuth2CallbackServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code =request.getParameter("code");
        if (code != null) {
            GoogleLogin googleLogin = new GoogleLogin();
            String accessToken = googleLogin.getToken(code);
            Map<String, String> userInfo = (Map<String, String>) getUserInfo(accessToken);

            saveUserToDatabase(userInfo);

            HttpSession session = request.getSession();
            session.setAttribute("user", userInfo);

            response.sendRedirect("home.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void saveUserToDatabase(Map<String, String> userInfo) {
    }

    public void destroy() {
    }
}