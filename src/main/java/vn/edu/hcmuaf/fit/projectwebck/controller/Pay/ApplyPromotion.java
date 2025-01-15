package vn.edu.hcmuaf.fit.projectwebck.controller.Pay;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.services.PromotionServices;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "ApplyPromotion", value = "/applyPromotion")
public class ApplyPromotion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int code = Integer.parseInt(request.getParameter("discountCode"));
        double total = Double.parseDouble(request.getParameter("total"));
        PromotionServices promotionServices = new PromotionServices();
        Integer value = promotionServices.getPromotionByUser(1, code);
        Map<String, Object> result = new HashMap<>();
        if (value == 0) {
            result.put("status", "error");
            result.put("message", "Mã khuyến mãi không tồn tại");
        } else {
            promotionServices.updatePromotionByUser(1,code,value--);
            double discountValue = value / 100.0;
            double totalAmount = total - (total * discountValue);
            result.put("status", "success");
            result.put("message", "Áp mã thành công");
            result.put("totalAmount", totalAmount);
        }
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(result));


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}