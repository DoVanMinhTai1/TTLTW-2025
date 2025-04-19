package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.stock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Stock;
import vn.edu.hcmuaf.fit.projectwebck.services.StockService;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "updateStock", value = "/updateStock")
public class UpdateStock extends HttpServlet {
    private final StockService stockService = new StockService();
    private final Gson gson = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        BufferedReader reader = request.getReader();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        int stockId = jsonObject.get("id").getAsInt();
        int productId = jsonObject.get("productId").getAsInt();
        int quantity = jsonObject.get("quantity").getAsInt();
        String name = jsonObject.get("name").getAsString();
        String addressLine = jsonObject.get("addressLine").getAsString();
        String district = jsonObject.get("district").getAsString();
        String stateOrProvince = jsonObject.get("stateOrProvince").getAsString();
        String country = jsonObject.get("country").getAsString();

        Stock stockToUpdate = new Stock(stockId, productId, quantity, name, addressLine, district, stateOrProvince, country);

        boolean updated = stockService.updateStock(stockToUpdate);

        Map<String, Object> result = new HashMap<>();
        result.put("success", updated);
        result.put("message", updated ? "Cập nhật tồn kho thành công" : "Cập nhật tồn kho thất bại");

        response.getWriter().write(gson.toJson(result));
    }

}
