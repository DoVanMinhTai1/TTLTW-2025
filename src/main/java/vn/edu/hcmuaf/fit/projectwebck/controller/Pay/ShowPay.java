package vn.edu.hcmuaf.fit.projectwebck.controller.Pay;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.cart.Cart;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Address;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.CartItem;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Transport;
import vn.edu.hcmuaf.fit.projectwebck.dto.product.ProductWithQuantity;
import vn.edu.hcmuaf.fit.projectwebck.services.AddressServices;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;
import vn.edu.hcmuaf.fit.projectwebck.services.TransportServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ShowPay", value = "/showPay")
public class ShowPay extends HttpServlet {
    private static final BigInteger MAX_LONG = new BigInteger(String.valueOf(Long.MAX_VALUE));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TransportServices transportServices = new TransportServices();
        List<Transport> list = transportServices.getAll();
        request.setAttribute("listTransport", list);
        String uIdParam = request.getParameter("uId");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<Integer,Integer> cartItems = new HashMap<Integer,Integer>();

        parameterMap.forEach((k, v) -> {
            if(k.startsWith("cart[")) {
                String productIdSubString = k.substring(k.indexOf("[") + 1, k.indexOf("]"));
                Integer productId = Integer.parseInt(productIdSubString);
                Integer quantity = Integer.parseInt(v[0]);
                cartItems.put(productId, quantity);
            }
        });
        List<CartItem> cartItemsList = new ArrayList<CartItem>();
        for (Map.Entry<Integer, Integer> entry : cartItems.entrySet()) {
            CartItem cartItem = new CartItem();
            cartItem.setProductId(entry.getKey());
            cartItem.setQuantity(entry.getValue());
            cartItem.setUserId(Integer.parseInt(uIdParam));
            cartItemsList.add(cartItem);
        }
        System.out.println("cartItemsList: " + cartItemsList);


        BigInteger uIdLong = new BigInteger(uIdParam);
        AddressServices addressServices = new AddressServices();

        int uId = 0;
        if (uIdLong.longValue() > 10000) {
            Address address = addressServices.getByThirtyPartyId(uIdParam);
            UserServices us = new UserServices();
            String email = us.getUserByThirtyPartyId(uIdParam).getEmail();
            request.setAttribute("address", address);
            String[] parts = address.getAddress().split(",");
            String number = parts.length > 0 ? parts[0].trim() : "";
            String ward = parts.length > 1 ? parts[1].trim() : "";
            String district = parts.length > 2 ? parts[2].trim() : "";
            String province = parts.length > 3 ? parts[3].trim() : "";

            request.setAttribute("number", number);
            request.setAttribute("ward", ward);
            request.setAttribute("district", district);
            request.setAttribute("province", province);
            request.setAttribute("email", email);
            request.getRequestDispatcher("Pay.jsp").forward(request, response);
        } else {

            uId = Integer.parseInt(request.getParameter("uId"));
            Address address = addressServices.getByIdOrigin(uId);
            UserServices us = new UserServices();
            String email = us.getUserById(uId).getEmail();
            request.setAttribute("address", address);

            String[] parts = address.getAddress().split(",");

            String number = parts.length > 0 ? parts[0].trim() : "";
            String ward = parts.length > 1 ? parts[1].trim() : "";
            String district = parts.length > 2 ? parts[2].trim() : "";
            String province = parts.length > 3 ? parts[3].trim() : "";

            request.setAttribute("number", number);
            request.setAttribute("ward", ward);
            request.setAttribute("district", district);
            request.setAttribute("province", province);
            request.setAttribute("email", email);

            ProductServices productServices = new ProductServices();
            List<ProductWithQuantity> productList = new ArrayList<>();
            for (CartItem cartItem : cartItemsList) {
                Product product = productServices.getById(cartItem.getProductId());
                ProductWithQuantity productWithQuantity = new ProductWithQuantity(cartItem.getQuantity(),product);
                productList.add(productWithQuantity);
            }
            System.out.println("productList: " + productList);
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("Pay.jsp").forward(request, response);


        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}