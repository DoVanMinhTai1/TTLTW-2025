package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Stock;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import vn.edu.hcmuaf.fit.projectwebck.services.StockService;

import java.io.*;

@WebServlet(name = "deleteStock", value = "/deleteStock")
public class DeleteStock extends HttpServlet {
    private final StockService stockService = new StockService();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String requestBody = sb.toString();
        JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);

        int stockId = jsonObject.get("id").getAsInt();

        boolean success = stockService.deleteStockById(stockId);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.write("{\"success\": " + success + "}");
        out.flush();
    }
}
