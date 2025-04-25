package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Product;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

@WebServlet(name = "deleteProductImageById", value = "/deleteProductImageById")
public class DeleteProductImage extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader br = req.getReader();
        JsonObject json = new Gson().fromJson(br, JsonObject.class);
        int productId = json.get("productId").getAsInt();
        int id = json.get("id").getAsInt();

        ProductServices productServices = new ProductServices();
        productServices.deleteProductImages(productId,id);

    }

    public void destroy() {
    }
}