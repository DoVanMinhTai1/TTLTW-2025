package vn.edu.hcmuaf.fit.doanwebtest.controller.Admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Order;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Product;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Promotion;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.User;
import vn.edu.hcmuaf.fit.doanwebtest.services.OrderServices;
import vn.edu.hcmuaf.fit.doanwebtest.services.ProductServices;
import vn.edu.hcmuaf.fit.doanwebtest.services.PromotionServices;
import vn.edu.hcmuaf.fit.doanwebtest.services.UserServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListProduct", value = "/showAdmin")
public class Show extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServices productService = new ProductServices();
        List<Product> products = productService.getAll();
        request.setAttribute("listproduct",products);// Lấy danh sách sản phẩm từ database
        UserServices userServices = new UserServices();
        List<User> listUser = userServices.getAllUsers();
        request.setAttribute("listuser",listUser);
        PromotionServices promotionServices = new PromotionServices();
        List<Promotion> listPromotion = promotionServices.getAllPromotion();
        request.setAttribute("listpromotion", listPromotion);
        OrderServices orderServices = new OrderServices();
        List<Order> listOrder = orderServices.getAllOrders();
        request.setAttribute("listorder", listOrder);

        request.getRequestDispatcher("Admin.jsp?runScript=option1").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
