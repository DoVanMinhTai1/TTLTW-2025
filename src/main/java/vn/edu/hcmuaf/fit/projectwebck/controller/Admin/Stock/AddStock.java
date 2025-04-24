package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Stock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;

import vn.edu.hcmuaf.fit.projectwebck.dao.model.Stock;
import vn.edu.hcmuaf.fit.projectwebck.services.StockService;

@WebServlet(name = "AddStock", value = "/AddStock")
public class AddStock extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        // Lấy dữ liệu từ JSON
        int productId = jsonObject.get("productId").getAsInt();
        int quantity = jsonObject.get("quantity").getAsInt();
        String name = jsonObject.get("name").getAsString();
        String addressLine = jsonObject.get("addressLine").getAsString();
        String district = jsonObject.get("district").getAsString();
        String stateOrProvince = jsonObject.get("stateOrProvince").getAsString();
        String country = jsonObject.get("country").getAsString();

        // Tạo đối tượng Stock
        Stock stock = new Stock(0, productId, quantity, name, addressLine, district, stateOrProvince, country);

        // Lưu vào cơ sở dữ liệu
        StockService stockService = new StockService();
        stockService.addStock(stock);

        // Trả kết quả JSON
        response.setContentType("application/json");
        response.getWriter().write("{\"status\": \"success\"}");
    }
}
