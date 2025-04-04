package vn.edu.hcmuaf.fit.projectwebck.controller.Pay;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.cart.Cart;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Address;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Transport;
import vn.edu.hcmuaf.fit.projectwebck.services.AddressServices;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;
import vn.edu.hcmuaf.fit.projectwebck.services.TransportServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@WebServlet(name = "ShowPay", value = "/showPay")
public class ShowPay extends HttpServlet {
    private static final BigInteger MAX_LONG = new BigInteger(String.valueOf(Long.MAX_VALUE));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TransportServices transportServices = new TransportServices();
        List<Transport> list = transportServices.getAll();
        request.setAttribute("listTransport", list);
        String uIdParam = request.getParameter("uId");
        String pIdParam = request.getParameter("productId");
        BigInteger uIdLong = new BigInteger(uIdParam);
        AddressServices addressServices = new AddressServices();

        int uId = 0;
        if (uIdLong.longValue() > 10000) {
//            String uIdParam = request.getParameter("uId")
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

            HttpSession session = request.getSession();
            if (pIdParam == null) {
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart != null) {
                    request.setAttribute("cartList", cart.getList());
                }
            } else {
                ProductServices productServices = new ProductServices();
                Product p = productServices.getById(Integer.parseInt(pIdParam));
                request.setAttribute("product", p);
            }
            request.getRequestDispatcher("Pay.jsp").forward(request, response);


        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}