package vn.edu.hcmuaf.fit.projectwebck.controller.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Address;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.AddressServices;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@WebServlet(name = "ShowCustomer", value = "/showCustomer")
public class ShowCustomer extends HttpServlet {
    private static final BigInteger MAX_LONG = new BigInteger(String.valueOf(Long.MAX_VALUE));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option");
        String uIdParam = request.getParameter("uId");
        UserServices userServices = new UserServices();
        BigInteger uIdLong = new BigInteger(uIdParam);
        User user = null;
        int uId = 0;
        if(uIdLong.compareTo(MAX_LONG) > 0) {
             user = userServices.getUserByThirtyPartyId(uIdParam);
        } else {

             uId =Integer.parseInt(request.getParameter("uId")) ;
             user = userServices.getUserById(uId);
        }

        request.setAttribute("user", user);

        switch (option) {
            case "option1":
                request.getRequestDispatcher("Customer.jsp?runScript=option1").forward(request, response);
                break;
            case "option2":
                OrderServices orderServices = new OrderServices();
                List<Order> orders = orderServices.getOrderByUserId(uId);
                for (Order order : orders) {
                    System.out.println(order.toString());
                }
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("Customer.jsp?runScript=option2").forward(request, response);
                break;
            case "option3":
                request.getRequestDispatcher("Customer.jsp?runScript=option3").forward(request, response);
                break;
            case "option4":
                AddressServices addressServices = new AddressServices();
                List<Address> listAddress = addressServices.getAllById(uId);
                request.setAttribute("listAddress", listAddress);
                request.getRequestDispatcher("Customer.jsp?runScript=option4").forward(request, response);
                break;
            case "option5":
//                PromotionServices promotionServices = new PromotionServices();
//                List<Promotion> listPromotion = promotionServices.getAllPromotion();
//                request.setAttribute("listpromotion", listPromotion);
//                request.getRequestDispatcher("Admin.jsp?runScript=option5").forward(request,response);
                break;
            default:

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}