package vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "showLogin", value = "/showLogin")
public class showLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ProductServices productService = new ProductServices();
//        List<Product> productsSeller = productService.getBestSellers();
//
//        List<Product> products = productService.getAllHome();
//
//        request.setAttribute("listProductBestSeller",productsSeller);
//        request.setAttribute("allProduct",products);
        request.getRequestDispatcher("jsp/SignInUp.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
