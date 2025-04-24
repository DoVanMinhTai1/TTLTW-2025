package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.stock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import vn.edu.hcmuaf.fit.projectwebck.dao.model.Stock;
import vn.edu.hcmuaf.fit.projectwebck.services.StockService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "getAllStock", value = "/getAllStock")
public class GetAllStock extends HttpServlet {
    private final StockService stockService = new StockService();
    private final Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Stock> allStocks = stockService.getAllStocks();

        String json = gson.toJson(allStocks);
        response.getWriter().println(json);
    }
}
