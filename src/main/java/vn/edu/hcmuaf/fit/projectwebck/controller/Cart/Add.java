package vn.edu.hcmuaf.fit.projectwebck.controller.Cart;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.edu.hcmuaf.fit.projectwebck.dao.cart.Cart;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.CartItem;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.CartItemService;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "Add", value = "/add-cart")
public class Add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
        //        ProductServices ps = new ProductServices();
//        Product pid = ps.getDetail(request.getParameter("pid"));
//        if (pid == null) {
//            response.sendRedirect("list-product?addCart=false");
//        }
//        HttpSession session = request.getSession(true);
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart == null) cart = new Cart();
//        cart.add(pid);
//        session.setAttribute("cart", cart);
//
//        session.setAttribute("total", cart.getTotal());
//        response.sendRedirect("showDetail?addCart=ok&id="+pid.getId());
        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            sb.append(line);
        }
        String json = sb.toString();
        CartItem cartItem = new Gson().fromJson(json, CartItem.class);
        CartItemService cartItemService = new CartItemService();
        try {
            cartItemService.addCartItem(cartItem.getUserId(),cartItem.getQuantity(),cartItem.getProductId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.println(gson.toJson(cartItem));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}