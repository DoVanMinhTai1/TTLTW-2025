package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Promotion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Promotion;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.LogsServices;
import vn.edu.hcmuaf.fit.projectwebck.services.PromotionServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemovePromotion", value = "/removePromotion")
public class RemovePromotion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("poid");
        int poid = Integer.parseInt(id);
        PromotionServices promotionServices = new PromotionServices();
        Promotion promotionRm=promotionServices.getPromotionById(poid);
        promotionServices.removePromotion(poid);
        List<Promotion> listPromotion = promotionServices.getAllPromotion();
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserServices userService = new UserServices();
            User user = (User) session.getAttribute("user"); ;
            if (user != null) {
                // Gọi LogService để ghi log
                LogsServices logService = new LogsServices();
                logService.danger(user.getUsername()+" đã xóa một sản phẩm",user.getId(),"Xóa sản phẩm",promotionRm.toString(),"");
            }
        }
        request.setAttribute("listpromotion", listPromotion);
        request.setAttribute("message", "Xóa khuyến mãi thành công");
        request.getRequestDispatcher("Admin.jsp?runScript=option5").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}