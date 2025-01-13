package vn.edu.hcmuaf.fit.projectwebck.controller.Home.Detail;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "showDetail", value = "/showDetail")
public class showDetail extends  HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductServices productService= new ProductServices();
        Product detail = productService.getDetail(id);
        request.setAttribute("p",detail);
        request.getRequestDispatcher("jsp/Detail.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
