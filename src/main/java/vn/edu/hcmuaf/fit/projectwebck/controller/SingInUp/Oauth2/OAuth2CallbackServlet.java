package vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp.Oauth2;

import java.io.*;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.UserDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.GoogleProfile;

import static vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp.Oauth2.GoogleLogin.getUserInfo;

@WebServlet(name = "OAuth2CallbackServletServlet", value = "/OAuth2CallbackServlet-servlet")
public class OAuth2CallbackServlet extends HttpServlet {
    private String message;
    private UserDao userDao;

    public void init() {

        message = "Hello World!";
        userDao = new UserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code =request.getParameter("code");
        if (code != null) {
            GoogleLogin googleLogin = new GoogleLogin();
            String accessToken = googleLogin.getToken(code);
//            Map<String, String> userInfo = (Map<String, String>) getUserInfo(accessToken);
            GoogleProfile gp = googleLogin.getUserInfo(accessToken);
            saveUserToDatabase(gp);

            HttpSession session = request.getSession();
            session.setAttribute("user", gp);

            response.sendRedirect("showAll");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void saveUserToDatabase(GoogleProfile gp) {
        Jdbi jdbi = JDBIConect.get();

        String baseUserNamed = gp.getEmail().split("@")[0];

        String userName = generateUniqueUserName(baseUserNamed);

        jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO users (username, role, fullName, email) " +
                        "VALUES (:username, :decentralization, :fullName, :email)")
                .bind("username", userName)
                .bind("decentralization", 0)
                .bind("fullName", gp.getName())
                .bind("email", gp.getEmail())
                .execute());

    }

    private String generateUniqueUserName(String baseUserNamed) {
        String userName = baseUserNamed;

        while(userDao.findUserByUsername(userName) != null) {
            userName = baseUserNamed + "_" + UUID.randomUUID().toString().substring(0, 4);
        }
        return userName;
    }


    public void destroy() {
    }
}