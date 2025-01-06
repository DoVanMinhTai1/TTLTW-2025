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

@WebServlet(name = "SearchPromotion", value = "/searchPromotion")
public class SearchPromotion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int keyword = Integer.parseInt(request.getParameter("searchPromotion")); // Lấy từ khóa tìm kiếm từ request
        PromotionServices promotionServices = new PromotionServices();
        // Tìm kiếm sản phẩm theo id
        List<Promotion> promotion = promotionServices.searchById(keyword);
        // Đặt danh sách vào request và chuyển đến trang JSP
        request.setAttribute("listpromotion", promotion);

        request.getRequestDispatcher("Admin.jsp?runScript=option5").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}