package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.productdiscounts;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

@WebServlet(name = "deleteProductDiscount", value = "/deleteProductDiscount")
public class DelProductDiscount extends HttpServlet {
    private String message;
    private final ProductServices productServices = new ProductServices();
    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//        BufferedReader reader = req.getReader();
//        StringBuilder sb = new StringBuilder();
//        Gson gson = new Gson();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//            sb.append(line);
//        }
//        String requestBody = sb.toString();
//        JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
//        int id = jsonObject.get("id").getAsInt();
//        boolean success = productServices.deleteProductDiscount(id);
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        PrintWriter out = resp.getWriter();
//        out.write("{\"success\": " + success + "}");
//        out.flush();
//    }
//
//


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            sb.append(line);
        }
        String requestBody = sb.toString();
        JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
        int id = jsonObject.get("id").getAsInt();
        boolean success = productServices.deleteProductDiscount(id);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write("{\"success\": " + success + "}");
        out.flush();
    }

    public void destroy() {
    }
}