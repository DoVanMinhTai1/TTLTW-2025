package vn.edu.hcmuaf.fit.doanwebtest.controller.Admin.Order;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Order;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.OrderDetail;
import vn.edu.hcmuaf.fit.doanwebtest.services.OrderServices;

import java.io.IOException;
import java.util.List;

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