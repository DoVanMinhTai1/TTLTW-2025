package vn.edu.hcmuaf.fit.projectwebck.controller.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.*;
import vn.edu.hcmuaf.fit.projectwebck.services.*;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ListProduct", value = "/showAdmin")
public class Show extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServices productService = new ProductServices();
        List<Product> products = productService.getAll();
        request.setAttribute("listproduct", products);
        OrderServices orderServices = new OrderServices();
        List<Order> listOrder = orderServices.getAllOrders();
        request.setAttribute("listorder", listOrder);
        UserServices userServices = new UserServices();
        List<User> listUser = userServices.getAllUsers();
        request.setAttribute("listuser", listUser);
        OrderDetailServices orderDetailServices = new OrderDetailServices();
        List<OrderDetail> listOrD = orderDetailServices.getAllOrderDetails();
        request.setAttribute("listordetail", listOrD);
        LogsServices logsServices = new LogsServices();
        List<Log> listLog = logsServices.getAllLogs();
        request.setAttribute("listlog", listLog);

        //show order
//        List<Order> listLatestOrders = orderServices.getLatestOrders();
        List<Map<String, Object>> listLatestOrders = orderServices.getLatestOrders();
        request.setAttribute("listlatestorders", listLatestOrders);

        List<Map<String, Object>> buyCustomer = orderServices.getCustomer();
        request.setAttribute("listCustomer", buyCustomer);
        double sum = 0;
        for (OrderDetail o : listOrD) {
            sum += o.getTotalAmount();
        }
        request.setAttribute("totalRevenue", sum);
        request.getRequestDispatcher("Admin.jsp?runScript=option1").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
