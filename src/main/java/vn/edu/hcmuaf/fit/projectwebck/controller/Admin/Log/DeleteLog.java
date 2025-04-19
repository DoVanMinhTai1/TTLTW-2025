package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Log;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.services.LogsServices;

import java.io.IOException;

@WebServlet(name = "DeleteLog", value = "/DeleteLog")
public class DeleteLog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logIdParam = request.getParameter("logId");
        if (logIdParam != null && !logIdParam.isEmpty()) {
            int logId = Integer.parseInt(logIdParam);

            // Gọi service để xóa
            LogsServices logsService = new LogsServices();
            boolean success = logsService.deleteByLogId(logId);

            if (success) {
                request.setAttribute("message", "Xóa thông báo thành công");
            } else {
                request.setAttribute("message", "Không tìm thấy thông báo để xóa");
            }
        } else {
            request.setAttribute("message", "Thiếu mã thông báo cần xóa");
        }
        request.getRequestDispatcher("Admin.jsp?runScript=option2").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}