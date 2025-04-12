package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Log;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.services.LogsServices;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;

import java.io.IOException;

@WebServlet(name = "GetLogByID", value = "/GetLogByID")
public class GetLogByID extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logId = request.getParameter("logId");

        Gson gson = new Gson();
        response.setContentType("application/json");
        LogsServices service = new LogsServices();
        if (logId != null) {
            response.getWriter().write(gson.toJson(service.getByLogId(Integer.parseInt(request.getParameter("logId")))));
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}