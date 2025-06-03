package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Stock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Stock;
import vn.edu.hcmuaf.fit.projectwebck.services.StockService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "getStock", value = "/getStock")
public class GetStock extends HttpServlet {
    private final StockService stockService = new StockService();
    private final Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println(request.getParameter("id"));
        int id = Integer.parseInt(request.getParameter("id"));
        StockService dao = new StockService();
        Stock stock = dao.getStockById(id);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(stock));
    }


}
