package vn.edu.hcmuaf.fit.projectwebck.controller.Home.Tubers;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "showTubers", value = "/showTubers")
public class showTubers extends  HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServices productService = new ProductServices();
        List<Product> tubers = productService.getAllTubers();

        request.setAttribute("listTubers",tubers);
        request.getRequestDispatcher("jsp/Tubers.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
