package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.User;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateInfCustomer", value = "/UpdateInfCustomer")
public class UpdateInfCustomer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đọc dữ liệu JSON từ request
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JsonObject json = JsonParser.parseString(sb.toString()).getAsJsonObject();

        // Lấy dữ liệu từ JSON
        String name = json.get("name").getAsString();
        int userId =Integer.parseInt( json.get("id").getAsString());
        String email = json.get("email").getAsString();
        String phone = json.get("phone").getAsString();
        UserServices userServices = new UserServices();
        // Cập nhật vào database
        boolean success = userServices.updateUserCustomer(userId,name,email,phone);

        // Trả về JSON response
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JsonObject res = new JsonObject();
        res.addProperty("success", success);
        out.print(res.toString());
        out.flush();
    }
}