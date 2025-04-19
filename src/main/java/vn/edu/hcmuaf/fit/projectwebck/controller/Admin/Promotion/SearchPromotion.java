package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Promotion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Promotion;
import vn.edu.hcmuaf.fit.projectwebck.services.PromotionServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchPromotion", value = "/searchPromotion")
public class SearchPromotion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword"); // Lấy từ khóa tìm kiếm từ request
        PromotionServices services = new PromotionServices();
        List<Promotion> promotions;
        if (keyword != null && !keyword.isEmpty()) {
            try {
                int id = Integer.parseInt(keyword);
                promotions = services.searchById(id);
            } catch (NumberFormatException e) {
                promotions = new ArrayList<>();
            }
        } else {
            promotions = services.getAllPromotion();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        response.getWriter().write(gson.toJson(promotions));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}