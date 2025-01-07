package vn.edu.hcmuaf.fit.projectwebck.controller.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Promotion;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;
import vn.edu.hcmuaf.fit.projectwebck.services.PromotionServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowOption", value = "/showOption")
public class ShowOption extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option");
        switch (option) {
            case "option1":
                request.getRequestDispatcher("Admin.jsp?runScript=option1").forward(request,response);
                break;
            case "option2":
                ProductServices productService = new ProductServices();
                List<Product> products = productService.getAll();
                request.setAttribute("listproduct",products);
                request.getRequestDispatcher("Admin.jsp?runScript=option2").forward(request,response);
                break;
            case "option3":
                UserServices userServices = new UserServices();
                List<User> listUser = userServices.getAllUsers();
                request.setAttribute("listuser",listUser);
                request.getRequestDispatcher("Admin.jsp?runScript=option3").forward(request,response);
                break;
            case "option4":
                OrderServices orderServices = new OrderServices();
                List<Order> listOrder = orderServices.getAllOrders();
                request.setAttribute("listorder", listOrder);
                request.getRequestDispatcher("Admin.jsp?runScript=option4").forward(request,response);
                break;
            case "option5":
                PromotionServices promotionServices = new PromotionServices();
                List<Promotion> listPromotion = promotionServices.getAllPromotion();
                request.setAttribute("listpromotion", listPromotion);
                request.getRequestDispatcher("Admin.jsp?runScript=option5").forward(request,response);
                break;
            default:

            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}