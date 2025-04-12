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
        PromotionServices promotionServices = new PromotionServices();
        Promotion promotion = promotionServices.getPromotionById(id);
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

        promotionServices.updatePromotion(promotionUpdate);

        // Lấy danh sách khuyến mãi cập nhật
        List<Promotion> listPromotion = promotionServices.getAllPromotion();
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserServices userService = new UserServices();
            User user = (User) session.getAttribute("user"); ;
            if (user != null) {
                // Gọi LogService để ghi log
                LogsServices logService = new LogsServices();
                logService.warning(user.getUsername()+" đã cập nhật 1 sản phẩm",user.getId(),"Cập nhật sản phẩm",promotion.toString(),promotionUpdate.toString());
            }
        }
        request.setAttribute("listpromotion", listPromotion);
        request.setAttribute("message", "Cập nhật khuyến mãi thành công");
        // Chuyển hướng về trang Admin với script phù hợp
        request.getRequestDispatcher("Admin.jsp?runScript=option5").forward(request, response);
    }
}