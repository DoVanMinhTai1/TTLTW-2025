package vn.edu.hcmuaf.fit.projectwebck.controller.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowCustomer", value = "/showCustomer")
public class ShowCustomer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option");
        switch (option) {
            case "option1":
                UserServices userServices = new UserServices();
                User user = userServices.getUserById(1);
                request.setAttribute("user", user);
                request.getRequestDispatcher("Customer.jsp?runScript=option1").forward(request, response);
                break;
            case "option2":
                OrderServices orderServices = new OrderServices();
                List<Order> orders = orderServices.getOrderByUserId(1);
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
                List<Address> listAddress = addressServices.getAllById(1);
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