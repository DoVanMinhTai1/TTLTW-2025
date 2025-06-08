package vn.edu.hcmuaf.fit.projectwebck.controller.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.ReturnRequestDAO;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.*;
import vn.edu.hcmuaf.fit.projectwebck.dto.product.ProductWithDiscount;
import vn.edu.hcmuaf.fit.projectwebck.services.*;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ListProduct", value = "/showAdmin")
public class Show extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("showLogin");
            return;
        }

        User user = (User) session.getAttribute("user");
        Role role = Role.fromId(user.getRole());
        System.out.println("Role ID of user: " + user.getRole());
        System.out.println("Session: " + session + ", User: " + session.getAttribute("user"));

        ProductServices productService = new ProductServices();
        if (role.hasPermission("MANAGE_VEGETABLES") || role.hasPermission("VIEW_DASHBOARD")) {
            List<Product> products = productService.getAll();
            request.setAttribute("listproduct", products);
        }
        OrderServices orderServices = new OrderServices();
        OrderDetailServices orderDetailServices = new OrderDetailServices();
        if (role.hasPermission("MANAGE_ORDERS") || role.hasPermission("VIEW_DASHBOARD")) {
            List<Order> listOrder = orderServices.getAllOrders();
            request.setAttribute("listorder", listOrder);
            List<Map<String, Object>> listLatestOrders = orderServices.getLatestOrders();
            request.setAttribute("listlatestorders", listLatestOrders);
            List<OrderDetail> listOrD = orderDetailServices.getAllOrderDetails();
            request.setAttribute("listordetail", listOrD);
            double sum = 0;
            for (OrderDetail o : listOrD) {
                sum += o.getTotalAmount();
            }
            request.setAttribute("totalRevenue", sum);
            List<Map<String, Object>> buyCustomer = orderServices.getCustomer();
            request.setAttribute("listCustomer", buyCustomer);
        }

        UserServices userServices = new UserServices();
        if (role.hasPermission("MANAGE_USERS") || role.hasPermission("VIEW_DASHBOARD")) {
            List<User> listUser = userServices.getAllUsers();
            request.setAttribute("listuser", listUser);
        }

        ProductServices productServices = new ProductServices();
        if (role.hasPermission("MANAGE_PRODUCT_PROMOTION") || role.hasPermission("VIEW_DASHBOARD")) {
            List<ProductWithDiscount> product = productService.getProductsWithDiscount();
            request.setAttribute("productWithDiscount", product);
        }
        PromotionServices promotionServices = new PromotionServices();
        if (role.hasPermission("MANAGE_PROMOTIONS") || role.hasPermission("VIEW_DASHBOARD")) {
            List<Promotion> listPromotion = promotionServices.getAllPromotion();
            List<Map<String, Object>> listAccount = orderServices.getListOfAccounts();
            request.setAttribute("listpromotion", listPromotion);
            request.setAttribute("listAccount", listAccount);
        }

        StockService stockService = new StockService();
        if (role.hasPermission("MANAGE_STOCK") || role.hasPermission("VIEW_DASHBOARD")) {
            List<Stock> allStocks = stockService.getAllStocks();
            request.setAttribute("stocks", allStocks);
        }

        ReturnRequestDAO returnRequestDAO = new ReturnRequestDAO();
        if (role.hasPermission("MANAGE_RETURN") || role.hasPermission("VIEW_DASHBOARD")) {
            List<ReturnRequest> allReturn = returnRequestDAO.getAllReturn();
            request.setAttribute("returnRequests", allReturn);
        }

        LogsServices logsServices = new LogsServices();
        List<Log> listLog = logsServices.getAllLogs();
        request.setAttribute("listlog", listLog);

        //show order
//        List<Order> listLatestOrders = orderServices.getLatestOrders();
        String defaultOption = request.getParameter("defaultOption");
        if (defaultOption == null) {
            defaultOption = "option1";
        }
        if (!role.hasPermission("VIEW_DASHBOARD")) {
            if (role.hasPermission("MANAGE_VEGETABLES")) {
                defaultOption = "option2";
            } else if (role.hasPermission("MANAGE_USERS")) {
                defaultOption = "option3";
            } else if (role.hasPermission("MANAGE_ORDERS")) {
                defaultOption = "option4";
            } else if (role.hasPermission("MANAGE_PROMOTIONS")) {
                defaultOption = "option5";
            } else if (role.hasPermission("MANAGE_PRODUCT_PROMOTION")) {
                defaultOption = "option6";
            } else if (role.hasPermission("MANAGE_STOCK")) {
                defaultOption = "option7";
            } else if (role.hasPermission("MANAGE_RETURN")) {
                defaultOption = "option8";
            } else {
                response.sendRedirect("showHome");
                return;
            }
        }
        System.out.println("defaultOption: " + defaultOption); // Gỡ lỗi
        request.setAttribute("runScript", defaultOption);
        request.getRequestDispatcher("Admin.jsp").forward(request, response);
//        request.getRequestDispatcher("Admin.jsp?runScript=" + defaultOption).forward(request, response);
    }

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
}
}
