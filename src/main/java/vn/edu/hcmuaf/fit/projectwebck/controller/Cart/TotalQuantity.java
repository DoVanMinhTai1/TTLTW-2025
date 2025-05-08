package vn.edu.hcmuaf.fit.projectwebck.controller.Cart;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.CartItemService;

@WebServlet(name = "TotalQuantity", value = "/TotalQuantity")
public class TotalQuantity extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user")  : null;

        int totalQuantity = 0;

        if (user != null) {
            int idUser = user.getId();
            CartItemService service = new CartItemService();
            totalQuantity = service.getTotalQuantityByUserId(idUser);
            System.out.println("Test Total quantity in Cart Servlet: " + totalQuantity);
        }

        out.println(totalQuantity);
    }

    public void destroy() {
    }
}