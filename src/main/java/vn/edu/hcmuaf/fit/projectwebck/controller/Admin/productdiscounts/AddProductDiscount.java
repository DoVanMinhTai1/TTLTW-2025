package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.productdiscounts;

import java.io.*;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.DiscoutType;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductDiscount;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

@WebServlet(name = "AddProductDiscount", value = "/AddProductDiscount")
public class AddProductDiscount extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        System.out.println("Received JSON: " + jsonObject.toString());

        Integer productId = jsonObject.get("productId").getAsInt();
        String discountTypeStr = jsonObject.get("discount_type").getAsString();
        DiscoutType discountType = DiscoutType.valueOf(discountTypeStr.toUpperCase());
        Double discountPercent = jsonObject.get("discount_percent").getAsDouble();
        Integer discount_price = jsonObject.get("discount_price").getAsInt();
        LocalDateTime startDateTime = gson.fromJson(jsonObject.get("startDateTime"), LocalDateTime.class);
        LocalDateTime endDateTime = gson.fromJson(jsonObject.get("endDateTime"), LocalDateTime.class);
        System.out.println("Product ID: " + productId);
        System.out.println("Discount Type: " + discountType);
        System.out.println("Discount Percent: " + discountPercent);
        System.out.println("Discount Price: " + discount_price);
        System.out.println("Start DateTime: " + startDateTime);
        System.out.println("End DateTime: " + endDateTime);

        vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductDiscount productDiscount = new ProductDiscount(
                productId,discountType,discountPercent,discount_price,startDateTime,endDateTime
        );

        ProductServices productServices = new ProductServices();
        productServices.save(productDiscount);

    }
}