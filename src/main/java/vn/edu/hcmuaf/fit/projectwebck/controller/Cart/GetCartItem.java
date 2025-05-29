package vn.edu.hcmuaf.fit.projectwebck.controller.Cart;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.CartItem;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.dto.cart.CartItemWithProduct;
import vn.edu.hcmuaf.fit.projectwebck.services.CartItemService;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "cartItem", value = "/cartItem")
public class GetCartItem extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("jsp/SignInUp.jsp").forward(request, response);
        } else {

            int id = user.getId();
            CartItemService cartItemService = new CartItemService();
            ProductServices productServices = new ProductServices();
            List<CartItem> cartItemList;
            List<Product> productList = new ArrayList<>();
            List<CartItemWithProduct> cartItemWithProduct = new ArrayList<>();
            try {
                cartItemList = cartItemService.getCartItems(id);
                List<Integer> ids = new ArrayList<>();
                if(cartItemList.size() > 0) {

                    for (CartItem cartItem : cartItemList) {
                        ids.add(cartItem.getProductId());
                    }
                }
                if(ids.size() > 0) {

                    productList = productServices.getByIds(ids);
                }
                Map<Integer, Product> productMap = new HashMap<>();
                for (Product product : productList) {
                  productMap.put(product.getId(), product);
                }
                for (CartItem cartItem : cartItemList) {
                    Product product = productMap.get(cartItem.getProductId());
                    if (product != null) {
                        CartItemWithProduct c = new CartItemWithProduct();
                        c.setProductId(product.getId());
                        c.setName(product.getName());
                        c.setPrice(product.getPrice());
                        c.setImage(product.getImage());
                        c.setQuantity(cartItem.getQuantity());
                        cartItemWithProduct.add(c);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



            request.setAttribute("cartItemWithProduct", cartItemWithProduct);
            request.getRequestDispatcher("/ShoppingCart.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}