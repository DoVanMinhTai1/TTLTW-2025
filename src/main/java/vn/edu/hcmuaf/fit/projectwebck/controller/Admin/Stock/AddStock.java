package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Stock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Stock;
import vn.edu.hcmuaf.fit.projectwebck.services.StockService;

@WebServlet(name = "uploadStock", value = "/UploadStock")
@MultipartConfig
public class AddStock extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        Steps:
        /*
            1.Get file from request
            2.Read Excel Data Using apache POI
            3.Loop through Each Row
            4.Extract Product Info
            5.Save Product
         */
        Part filePart = request.getPart("fileExcel");
        InputStream inputStream = filePart.getInputStream();

        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Stock> stocks = new ArrayList<>();
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                int productId = (int) row.getCell(0).getNumericCellValue();
                int quantity = (int) row.getCell(1).getNumericCellValue();
                String name = row.getCell(2).getStringCellValue();
                String addressLine = row.getCell(3).getStringCellValue();
                String district = row.getCell(4).getStringCellValue();
                String state = row.getCell(5).getStringCellValue();
                String country = row.getCell(6).getStringCellValue();

                Stock stock = new Stock();
                stock.setProductId(productId);
                stock.setQuantity(quantity);
                stock.setName(name);
                stock.setAddressLine(addressLine);
                stock.setDistrict(district);
                stock.setStateOrProvince(state);
                stock.setCountry(country);

                stocks.add(stock);


            }
            StockService stockService = new StockService();
            stockService.addStock(stocks);

            response.setContentType("text/plain");
            response.getWriter().println("Stock uploaded and saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
