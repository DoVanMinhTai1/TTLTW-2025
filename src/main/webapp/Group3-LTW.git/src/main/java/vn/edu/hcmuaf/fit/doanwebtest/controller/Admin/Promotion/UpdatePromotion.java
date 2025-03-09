package vn.edu.hcmuaf.fit.doanwebtest.controller.Admin.Promotion;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Promotion;
import vn.edu.hcmuaf.fit.doanwebtest.services.PromotionServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdatePromotion", value = "/updatePromotion")
public class UpdatePromotion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        int id = Integer.parseInt(request.getParameter("poid"));
        String name = request.getParameter("PromotionName");
        String startDate = request.getParameter("StartDate");
        String endDate = request.getParameter("EndDate");
        String valueStr = request.getParameter("Value");

        // Chuyển đổi giá trị từ chuỗi sang số nguyên
        int value = Integer.parseInt(valueStr);

        // Tạo đối tượng Promotion và gán các giá trị
        Promotion promotionUpdate = new Promotion();
        promotionUpdate.setId(id);
        promotionUpdate.setName(name);
        promotionUpdate.setStartDate(startDate);
        promotionUpdate.setEndDate(endDate);
        promotionUpdate.setValue(value);

        // Gọi service để cập nhật khuyến mãi
        PromotionServices promotionServices = new PromotionServices();
        promotionServices.updatePromotion(promotionUpdate);

        // Lấy danh sách khuyến mãi cập nhật
        List<Promotion> listPromotion = promotionServices.getAllPromotion();
        request.setAttribute("listpromotion", listPromotion);

        // Chuyển hướng về trang Admin với script phù hợp
        request.getRequestDispatcher("Admin.jsp?runScript=option5").forward(request, response);
    }
}