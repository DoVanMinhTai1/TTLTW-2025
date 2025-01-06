package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Order;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;
import java.io.IOException;

@WebServlet(name = "Detail", value = "/detailOrder")
public class Detail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");

        Gson gson = new Gson();
        response.setContentType("application/json");
        OrderServices service = new OrderServices();
        if (orderId != null) {
            response.getWriter().write(gson.toJson(service.getOrderDetailsByOrderId(Integer.parseInt(request.getParameter("orderId")))));
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}