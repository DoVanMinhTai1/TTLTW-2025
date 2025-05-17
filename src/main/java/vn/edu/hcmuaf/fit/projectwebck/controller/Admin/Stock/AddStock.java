package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Stock;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Stock;
import vn.edu.hcmuaf.fit.projectwebck.dto.stock.CheckAddress;
import vn.edu.hcmuaf.fit.projectwebck.dto.stock.StockKey;
import vn.edu.hcmuaf.fit.projectwebck.services.StockService;

@WebServlet(name = "uploadStock", value = "/UploadStock")
@MultipartConfig
public class AddStock extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        Steps:
        /*
            1.Get List Object Stock from request
            3.Loop
            4.Extract Product Info
            5.Save Product
         */

        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        JsonObject jsonObject = new Gson().fromJson(sb.toString(), JsonObject.class);
        JsonArray stocksArray = jsonObject.getAsJsonArray("stocks");

        List<Stock> stocks = new Gson().fromJson(
                stocksArray,
                new TypeToken<List<Stock>>() {
                }.getType()
        );

        StockService stockService = new StockService();
        Map<StockKey, Integer> stockMap = new HashMap<>();

        for (Stock stock : stocks) {
            StockKey key = new StockKey(stock.getProductId(),
                    stock.getAddressLine(),
                    stock.getDistrict(),
                    stock.getStateOrProvince(),
                    stock.getCountry());

            stockMap.put(key,stockMap.getOrDefault(key,0) + stock.getQuantity());
        }

        List<Stock> stockInsert = new ArrayList<>();

        for (Map.Entry<StockKey, Integer> entry : stockMap.entrySet()) {
            StockKey key = entry.getKey();
            int quantity = entry.getValue();

            Stock exists = stockService.findStockByKey(key);

            if(exists != null) {
                exists.setQuantity(exists.getQuantity() + quantity);
                stockService.updateStock(exists);
            } else {
                Stock newStock = new Stock();
                newStock.setProductId(key.getProductId());
                newStock.setQuantity(quantity);
                newStock.setAddressLine(key.getAddressLine());
                newStock.setDistrict(key.getDistrict());
                newStock.setStateOrProvince(key.getStateOrProvince());
                newStock.setCountry(key.getCountry());
                stockInsert.add(newStock);
            }
        }
        stockService.addStock(stockInsert);

        response.setContentType("text/plain");
        response.getWriter().println("Stock uploaded and saved successfully!");
    }

}
