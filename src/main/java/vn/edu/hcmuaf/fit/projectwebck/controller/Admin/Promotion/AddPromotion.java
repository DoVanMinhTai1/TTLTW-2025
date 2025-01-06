package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Promotion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Promotion;
import vn.edu.hcmuaf.fit.projectwebck.services.PromotionServices;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddPromotion", value = "/addPromotion")
public class AddPromotion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String promotionName = request.getParameter("PromotionName");
        String startDate = request.getParameter("StartDate");
        String endDate = request.getParameter("EndDate");
        String valueStr = request.getParameter("Value");

        // Convert value to integer
        int value = Integer.parseInt(valueStr);

        // Create a Promotion object and set its fields
        Promotion promotion = new Promotion();
        promotion.setName(promotionName);
        promotion.setStartDate(startDate);
        promotion.setEndDate(endDate);
        promotion.setValue(value);

        // Call the service to insert the promotion
        PromotionServices promotionServices = new PromotionServices();
        promotionServices.insertPromotion(promotion);

        // Retrieve the updated list of promotions
        List<Promotion> listPromotion = promotionServices.getAllPromotion();
        request.setAttribute("listpromotion", listPromotion);

        // Forward to the Admin page with the appropriate script to load
        request.getRequestDispatcher("Admin.jsp?runScript=option5").forward(request, response);


    }
}