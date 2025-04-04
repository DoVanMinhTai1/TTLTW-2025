package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.productdiscounts;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

@WebServlet(name = "productDiscount", value = "/productDiscount")
public class ProductDiscount extends HttpServlet {
    private final ProductServices productServices = new ProductServices();
    private final Gson gson =  new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public ProductDiscount() {
        super();
    }




    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        List<Product> products = productServices.getAll();
        response.getWriter().println(gson.toJson(products));

    }

    public void destroy() {
    }
}