package vn.edu.hcmuaf.fit.projectwebck.controller.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.*;
import vn.edu.hcmuaf.fit.projectwebck.dto.product.ProductWithDiscount;
import vn.edu.hcmuaf.fit.projectwebck.services.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ShowOption", value = "/showOption")
public class ShowOption extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("showLogin");
            return;
        }

        User user = (User) session.getAttribute("user");
        Role role = Role.fromId(user.getRole());
        String option = request.getParameter("option");

        if (!hasPermission(role, option)) {
            response.sendRedirect("access-denied.jsp");
            return;
        }

        ProductServices productService = new ProductServices();
        List<Product> products = productService.getAll();
        OrderServices orderServices = new OrderServices();
        List<Order> listOrder = orderServices.getAllOrders();
        UserServices userServices = new UserServices();
        List<User> listUser = userServices.getAllUsers();
        OrderDetailServices orderDetailServices = new OrderDetailServices();
        List<OrderDetail> listOrD = orderDetailServices.getAllOrderDetails();
        LogsServices logsServices = new LogsServices();
        List<Log> listLog = logsServices.getAllLogs();
//        request.setAttribute("listordetail", listOrD);

//        double sum = 0;
//        for (OrderDetail o : listOrD) {
//            sum += o.getTotalAmount();
//        }
//        request.setAttribute("totalRevenue", sum);

        switch (option) {
            case "option1":
                List<Map<String, Object>> listLatestOrders = orderServices.getLatestOrders();

                List<Map<String, Object>> buyCustomer = orderServices.getCustomer();

//                double sum = 0;
//                for (OrderDetail o : listOrD) {
//                    sum += o.getTotalAmount();
//                }
                double sum = listOrD.stream().mapToDouble(OrderDetail::getTotalAmount).sum();

                request.setAttribute("totalRevenue", sum);
                request.setAttribute("listproduct", products);
                request.setAttribute("listorder", listOrder);
                request.setAttribute("listuser", listUser);
                request.setAttribute("listlatestorders", listLatestOrders);
                request.setAttribute("listCustomer", buyCustomer);
                request.setAttribute("listlog", listLog);
 //               request.getRequestDispatcher("Admin.jsp?runScript=option1").forward(request,response);
                break;
            case "option2":
//                 productService = new ProductServices();
//                 products = productService.getAll();
                request.setAttribute("listproduct",products);
                request.setAttribute("listlog", listLog);
//                request.getRequestDispatcher("Admin.jsp?runScript=option2").forward(request,response);
                break;
            case "option3":
                request.setAttribute("listuser",listUser);
                request.setAttribute("listlog", listLog);
//                request.getRequestDispatcher("Admin.jsp?runScript=option3").forward(request,response);
                break;
            case "option4":
                request.setAttribute("listorder", listOrder);
                request.setAttribute("listlog", listLog);
//                request.getRequestDispatcher("Admin.jsp?runScript=option4").forward(request,response);
                break;
            case "option5":
                PromotionServices promotionServices = new PromotionServices();
                List<Promotion> listPromotion = promotionServices.getAllPromotion();
                request.setAttribute("listpromotion", listPromotion);
                request.setAttribute("listlog", listLog);
//                request.getRequestDispatcher("Admin.jsp?runScript=option5").forward(request,response);
                break;
            case "option6":
                List<ProductWithDiscount> product = productService.getProductsWithDiscount();
                request.setAttribute("productWithDiscount", product);
                request.setAttribute("listlog", listLog);
//                request.getRequestDispatcher("Admin.jsp?runScript=option6").forward(request,response);
                break;
            default:

            break;
        }

        request.setAttribute("runScript", option);
        request.getRequestDispatcher("Admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private boolean hasPermission(Role role, String option) {
        if (option == null) return false;
        switch (option) {
            case "option1":
                return role.hasPermission("VIEW_DASHBOARD");
            case "option2":
                return role.hasPermission("MANAGE_VEGETABLES");
            case "option3":
                return role.hasPermission("MANAGE_USERS");
            case "option4":
                return role.hasPermission("MANAGE_ORDERS");
            case "option5":
                return role.hasPermission("MANAGE_PROMOTIONS");
            case "option6":
                return role.hasPermission("MANAGE_PRODUCT_PROMOTION");
            default:
                return false;
        }
    }
}