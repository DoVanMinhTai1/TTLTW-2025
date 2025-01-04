package vn.edu.hcmuaf.fit.doanwebtest.controller.Admin.Promotion;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Promotion;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.User;
import vn.edu.hcmuaf.fit.doanwebtest.services.PromotionServices;
import vn.edu.hcmuaf.fit.doanwebtest.services.UserServices;

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