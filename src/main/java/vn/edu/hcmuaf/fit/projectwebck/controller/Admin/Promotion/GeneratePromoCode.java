package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Promotion;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Promotion;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;
import vn.edu.hcmuaf.fit.projectwebck.services.PromotionServices;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "GeneratePromoCode", value = "/GeneratePromoCode")
public class GeneratePromoCode extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] listAccountId = request.getParameterValues("listAccount");
        int promotionId = Integer.parseInt(request.getParameter("maKM"));
        List<Integer> userIds = Arrays.stream(listAccountId)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        PromotionServices promotionServices = new PromotionServices();
        OrderServices orderServices = new OrderServices();
        List<Promotion> listPromotion = promotionServices.getAllPromotion();
        List<Map<String, Object>> listAccount = orderServices.getListOfAccounts();

        boolean isValid = promotionServices.isValidPromotionUserCount(promotionId, userIds.size());

        if (!isValid) {
            request.setAttribute("message", "Vượt quá số lượng người dùng cho mã này");
            request.setAttribute("listpromotion", listPromotion);
            request.setAttribute("listAccount", listAccount);
            request.getRequestDispatcher("Admin.jsp?runScript=option5").forward(request, response);
            return;
        }
        promotionServices.insertPromotionUserList(promotionId, userIds);
        request.setAttribute("message", "Phát khuyến mãi thành công ");
        request.setAttribute("listpromotion", listPromotion);
        request.setAttribute("listAccount", listAccount);
        request.getRequestDispatcher("Admin.jsp?runScript=option5").forward(request, response);
    }
}