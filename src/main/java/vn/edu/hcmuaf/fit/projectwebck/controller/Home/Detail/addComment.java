package vn.edu.hcmuaf.fit.projectwebck.controller.Home.Detail;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Comment;
import vn.edu.hcmuaf.fit.projectwebck.services.CommentServices;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "addComment", value = "/addComment")
public class addComment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        String content = request.getParameter("content");

        // Tạo comment object
        Comment comment = new Comment();
        comment.setProductId(productId);
        comment.setUserId(userId);
        comment.setUserName(userName);
        comment.setContent(content);
        comment.setCreatedAt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // Thêm bình luận
        CommentServices commentService = new CommentServices();
        commentService.insertComment(comment);
        // /Lấy lại danh sách bình luận mới
        List<Comment> commentList = commentService.getCommentsByProductId(productId);
        request.setAttribute("commentList", commentList);

        // Forward lại về trang chi tiết (giả sử là Detail.jsp)
        request.getRequestDispatcher("/jsp/Detail.jsp").forward(request, response);
    }

}