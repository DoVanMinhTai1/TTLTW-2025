package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.productdiscounts;

import java.io.*;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dto.ProductWithDiscount;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

@WebServlet(name = "getProductById", value = "/getProductById")
public class GetProductById extends HttpServlet {
    private String message;
    private final ProductServices productServices = new ProductServices();
    private final Gson gson =  new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();
    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        String productIdParam = request.getParameter("productId");

        if (productIdParam != null) {
            try {
                int productId = Integer.parseInt(productIdParam);

                ProductWithDiscount product = productServices.getProductsWithDiscountById(productId);

                if (product != null) {
                    String json = gson.toJson(product);
                    response.getWriter().write(json);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Invalid product ID\"}");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Missing product ID\"}");
        }
    }

    public void destroy() {
    }
}