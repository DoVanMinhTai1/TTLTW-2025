package vn.edu.hcmuaf.fit.projectwebck.controller.Home.Detail;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Comment;
import vn.edu.hcmuaf.fit.projectwebck.services.CommentServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "updateComment", value = "/updateComment")
public class updateComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(reader, JsonObject.class);
        String idParam = json.get("id").getAsString();
        String pidParam = json.get("pid").getAsString();
        String content = json.get("content").getAsString();

        CommentServices service = new CommentServices();
        boolean success = false;

        try {
            int commentId = Integer.parseInt(idParam);
            if (content != null && !content.trim().isEmpty()) {
                success = service.updateComment(commentId, content);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ.");
            return;
        }

        if (success) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            JsonObject result = new JsonObject();
            result.addProperty("success", true);
            result.addProperty("message", "Cập nhật bình luận thành công");
            response.getWriter().write(result.toString());
        } else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            JsonObject result = new JsonObject();
            result.addProperty("success", false);
            result.addProperty("message", "Cập nhật bình luận thất bại");
            response.getWriter().write(result.toString());
        }

    }
}
