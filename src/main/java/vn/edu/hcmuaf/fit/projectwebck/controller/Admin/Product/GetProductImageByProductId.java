package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Product;

import java.io.*;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductImage;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

@WebServlet(name = "GetProductImageByProductId", value = "/GetProductImageByProductId")
public class GetProductImageByProductId extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idRequest = request.getParameter("id");
        int id = Integer.parseInt(idRequest);

        ProductServices productServices = new ProductServices();
        List<ProductImage> productImage = productServices.getProductImageByProductId(id);
        System.out.println("Product Image ID: " + productImage);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(new Gson().toJson(productImage));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);


    }

    public void destroy() {
    }
}