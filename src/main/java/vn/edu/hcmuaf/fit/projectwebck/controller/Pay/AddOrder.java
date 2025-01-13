package vn.edu.hcmuaf.fit.projectwebck.controller.Pay;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.leangen.geantyref.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Map;

@WebServlet(name = "AddOrder", value = "/addOrder")
public class AddOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }


        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);

        int userId = jsonObject.get("userId").getAsInt();
        int addressId = jsonObject.get("addressId").getAsInt();
        double total = jsonObject.get("total").getAsDouble();
// Chuyển JSON thành Map
        Type cartType = new TypeToken<Map<Integer, Map<String, Double>>>() {}.getType();
        Map<Integer, Map<String, Double>> cartMap = gson.fromJson(jsonObject.get("cartMap"), cartType);

// Tạo đối tượng Order
        Order order = new Order();
        order.setUserId(userId);
        order.setAddressId(addressId);
        order.setDateOfBooking(LocalDate.now().toString());
        order.setStatus(0);
        order.setMoney(total);
        OrderServices service = new OrderServices();
        long orderId =service.insertOrderByUser(order, cartMap);

// Phản hồi kết quả
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(orderId));
    }
}