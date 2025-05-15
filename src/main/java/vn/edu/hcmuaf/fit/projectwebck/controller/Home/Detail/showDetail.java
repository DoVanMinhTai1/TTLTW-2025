package vn.edu.hcmuaf.fit.projectwebck.controller.Home.Detail;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Comment;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.CommentServices;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "showDetail", value = "/showDetail")
public class showDetail extends  HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductServices productService= new ProductServices();
        Product detail = productService.getDetail(id);
        request.setAttribute("p",detail);
        System.out.println("product in detail" + detail);
        CommentServices commentService= new CommentServices();
        List<Comment> commentList= commentService.getCommentsByProductId(id);
        request.setAttribute("commentList",commentList);

        // Lấy mô tả sản phẩm
        List<String> descriptions = productService.getDescription(id);
        List<String> sentences = new ArrayList<>();

        // Kiểm tra nếu mô tả không rỗng
        if (descriptions != null && !descriptions.isEmpty()) {
            for (String description : descriptions) {
                if (description != null) { // Kiểm tra null trước khi cắt
                    String[] splitSentences = description.split("\\.\\s*");
                    Collections.addAll(sentences, splitSentences);
                } else {
                    sentences.add("Không có mô tả nào."); // Thêm thông báo nếu description là null
                }
            }
        } else {
            sentences.add("Không có mô tả nào.");
        }

        //lay ngau nhien 5sp
        List<Product> relatedProducts= productService.getRandomRelatedProducts(Integer.parseInt(id));

        request.setAttribute("sentences", sentences);
        request.setAttribute("relatedProducts",relatedProducts);
        request.getRequestDispatcher("jsp/Detail.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
