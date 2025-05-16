package vn.edu.hcmuaf.fit.projectwebck.controller.Home.Detail;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Comment;
import vn.edu.hcmuaf.fit.projectwebck.services.CommentServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "removeComment", value = "/removeComment")
public class removeComment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String pidParam = request.getParameter("pid");
        CommentServices commentService = new CommentServices();
        if (idParam != null) {
            try {
                int commentId = Integer.parseInt(idParam);
                commentService.deleteComment(commentId);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu ID.");
        }
        List<Comment> commentList = commentService.getCommentsByProductId(pidParam);
        request.setAttribute("commentList", commentList);

        // Forward lại về trang chi tiết (giả sử là Detail.jsp)
        request.getRequestDispatcher("/jsp/Detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}