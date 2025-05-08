package vn.edu.hcmuaf.fit.projectwebck.controller.Cart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.CartItemService;

@WebServlet(name = "RemoveCartList", value = "/RemoveCartList")
public class RemoveCartList extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        BufferedReader reader = request.getReader();
        String line;
        StringBuilder responseBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }

        JsonArray jsonArray = new JsonParser().parseString(responseBuilder.toString()).getAsJsonArray();
        List<Integer> productIds = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            int productId = jsonObject.get("productId").getAsInt();
            productIds.add(productId);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();

        CartItemService cartItemService = new CartItemService();
        boolean success = cartItemService.deleteCartItem(id,productIds);

    }

    public void destroy() {
    }
}