package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.productdiscounts;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.DiscoutType;
import vn.edu.hcmuaf.fit.projectwebck.dto.ProductWithDiscount;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

@WebServlet(name = "updateProductDiscount", value = "/updateProductDiscount")
public class UpdateProductDiscount extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        BufferedReader reader = request.getReader();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        int id = jsonObject.get("idProductWithDiscount").getAsInt();
        int productId = jsonObject.get("productId").getAsInt();
        String discountTypeStr = jsonObject.get("discountType").getAsString();
        DiscoutType discountType = DiscoutType.valueOf(discountTypeStr.toUpperCase());
        Double discountPercent = jsonObject.get("discountPercent").getAsDouble();
        Integer discount_price = jsonObject.get("discountPrice").getAsInt();
        LocalDateTime startDateTime = gson.fromJson(jsonObject.get("startDate"), LocalDateTime.class);
        LocalDateTime endDateTime = gson.fromJson(jsonObject.get("endDate"), LocalDateTime.class);
        ProductServices productServices = new ProductServices();
        ProductWithDiscount productWithDiscountCons = new ProductWithDiscount(
                productId,discount_price,discountType,discountPercent,endDateTime,startDateTime
        );
        ProductWithDiscount productWithDiscount = productServices.updateProductWithDiscount(id,productWithDiscountCons);

        boolean success = productWithDiscount != null; // Thành công nếu không null
        String message = success ? "Cập nhật sản phẩm giảm giá thành công" : "Lỗi khi cập nhật sản phẩm giảm giá";
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", message);
        result.put("product", productWithDiscount);
        String json = gson.toJson(result);
        response.getWriter().write(json);
    }

    public void destroy() {
    }
}