package vn.edu.hcmuaf.fit.projectwebck.controller.Home.Detail;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import vn.edu.hcmuaf.fit.projectwebck.dao.cart.Cart;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;

@WebServlet(name = "addRelativePr", value = "/addRelativePr")
public class addRelativePr extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServices ps = new ProductServices();
        String currentId = request.getParameter("id");

        Product pid = ps.getDetail(request.getParameter("pid"));
        if (pid == null) {
            response.sendRedirect("list-product?addCart=false");
        }
        HttpSession session = request.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) cart = new Cart();
        cart.add(pid);
        session.setAttribute("cart", cart);

        session.setAttribute("total", cart.getTotal());
//        them tong tien vao session
//        System.out.println("currentIdParam: " + currentId);
//        System.out.println("pidParam: " + pid);

        response.sendRedirect("showDetail?addCart=ok&id=" + currentId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}