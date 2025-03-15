package vn.edu.hcmuaf.fit.projectwebck.controller.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;
import java.math.BigInteger;

@WebServlet(name = "ShowCustomerPage", value = "/showCustomerPage")
public class Show extends HttpServlet {
    private static final BigInteger MAX_LONG = new BigInteger(String.valueOf(Long.MAX_VALUE));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uIdParam = request.getParameter("uId");
        UserServices userServices = new UserServices();
//        int uId =Integer.parseInt(request.getParameter("uId")) ;
        try {
            BigInteger uIdLong = new BigInteger(uIdParam);
            if (uIdLong.compareTo(MAX_LONG) > 0) {

                User user = userServices.getUserByThirtyPartyId(uIdParam);
                System.out.println(user);
                request.setAttribute("user", user);
                request.getRequestDispatcher("Customer.jsp?runScript=option1").forward(request, response);

            } else {
//                BigInteger uId = new BigInteger(uIdParam);
                long uIdL =  uIdLong.longValue();
                int uId = (int) uIdL;
                User user = userServices.getUserById(uId);
                request.setAttribute("user", user);
                request.getRequestDispatcher("Customer.jsp?runScript=option1").forward(request, response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}