package vn.edu.hcmuaf.fit.projectwebck.controller.Home;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "showHome", value = "/showHome")
public class showHome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServices productService = new ProductServices();
        List<Product> productsSeller = productService.getBestSellers();

        List<Product> products = productService.getAllHome();

        request.setAttribute("listProductBestSeller",productsSeller);
        request.setAttribute("allProduct",products);
        request.getRequestDispatcher("jsp/Home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
