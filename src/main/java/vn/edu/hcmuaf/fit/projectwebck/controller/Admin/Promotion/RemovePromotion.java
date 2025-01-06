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

@WebServlet(name = "RemovePromotion", value = "/removePromotion")
public class RemovePromotion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("poid");
        int poid = Integer.parseInt(id);
        PromotionServices promotionServices = new PromotionServices();
        promotionServices.removePromotion(poid);
        List<Promotion> listPromotion = promotionServices.getAllPromotion();
        request.setAttribute("listpromotion", listPromotion);
        request.getRequestDispatcher("Admin.jsp?runScript=option5").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}