package vn.edu.hcmuaf.fit.projectwebck.controller.Cart;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.cart.Cart;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.CartItemService;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateCart", value = "/UpdateCart")
public class UpdateCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int productId = Integer.parseInt(request.getParameter("pid"));
//        String expression = request.getParameter("expression");
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        ProductServices productService = new ProductServices();
//        int maxQuantity = productService.getMass(productId);
//        if (expression.equals("plus")){
//            if (quantity < maxQuantity) {
//                quantity++;
//            } else {
//                // Nếu số lượng vượt quá tồn kho, lưu thông báo lỗi vào request
//                request.setAttribute("error", "Vượt quá số lượng trong kho");
//                // Trả về kết quả và hiển thị lỗi bằng cách chuyển hướng
//                request.getRequestDispatcher("ShoppingCart.jsp").forward(request, response);
//                return; // Dừng việc thực thi tiếp
//            }
//        }else if (expression.equals("minus") && quantity > 1){
//            quantity --;
//        }
//        // Cập nhật giỏ hàng trong session
//        HttpSession session = request.getSession(true);
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart == null) cart = new Cart();
//        cart.update(productId, quantity);
//        session.setAttribute("cart", cart);
//        session.setAttribute("total", cart.getTotal());
//        // Quay lại trang giỏ hàng hoặc trả về kết quả
//        response.sendRedirect("ShoppingCart.jsp");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        CartItemService cartItemService = new CartItemService();
        try {
            boolean success = cartItemService.updateCartItem(userId, productId, quantity);
            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"success\": true, \"message\": \"Updated cart item successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        BufferedReader reader = request.getReader();
        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            output.append(line);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        JsonObject jsonObject = new JsonParser().parse(output.toString()).getAsJsonObject();
        int productId = jsonObject.get("productId").getAsInt();
        int quantity = jsonObject.get("quantity").getAsInt();
        CartItemService cartItemService = new CartItemService();
        try {
            boolean success = cartItemService.updateCartItem(userId, productId, quantity);
            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"success\": true, \"message\": \"Updated cart item successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}