package vn.edu.hcmuaf.fit.projectwebck.controller.Pay;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;

@WebServlet(name = "VNPayPaymentServlet", value = "/create-vnpay-payment")
public class VNPayPaymentServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        // Đọc JSON từ request
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        // Parse JSON
        JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
        String userId = jsonObject.get("userId").getAsString();
        int addressId = jsonObject.get("addressId").getAsInt();
        double total = jsonObject.get("total").getAsDouble();
//        Map<Integer, Map<String, Double>> cartMap = gson.fromJson(jsonObject.get("cartMap"), Map.class);
        TypeToken<Map<Integer, Map<String, Object>>> cartType = new TypeToken<Map<Integer, Map<String, Object>>>() {};
        Map<Integer, Map<String, Object>> rawCartMap = gson.fromJson(jsonObject.get("cartMap"), cartType.getType());
        Map<Integer, Map<String, Double>> cartMap = new TreeMap<>();

        JsonArray  productToRemove = new JsonArray();
        for (Map.Entry<Integer, Map<String, Object>> entry : rawCartMap.entrySet()) {
            Map<String, Object> rawProductInfo = entry.getValue();
            Map<String, Double> productInfo = new TreeMap<>();
            Integer productId = entry.getKey();  // đây là productId

            // Xử lý quantity
            Object quantityObj = rawProductInfo.get("quantity");
            double quantity;
            if (quantityObj instanceof String) {
                try {
                    quantity = Double.parseDouble((String) quantityObj);
                } catch (NumberFormatException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    JsonObject errorResponse = new JsonObject();
                    errorResponse.addProperty("message", "Số lượng không hợp lệ cho sản phẩm ID: " + entry.getKey());
                    response.getWriter().write(gson.toJson(errorResponse));
                    return;
                }
            } else if (quantityObj instanceof Number) {
                quantity = ((Number) quantityObj).doubleValue(); // Xử lý Integer hoặc Double
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                JsonObject errorResponse = new JsonObject();
                errorResponse.addProperty("message", "Số lượng không hợp lệ cho sản phẩm ID: " + entry.getKey());
                response.getWriter().write(gson.toJson(errorResponse));
                return;
            }

            JsonObject item = new JsonObject();
            item.addProperty("productId", entry.getKey());
            item.addProperty("quantity", quantity);
            productToRemove.add(item);

            // Xử lý price
            Object priceObj = rawProductInfo.get("price");
            double price = priceObj instanceof Number ? ((Number) priceObj).doubleValue() : 0.0;

            productInfo.put("quantity", quantity);
            productInfo.put("price", price);
            cartMap.put(entry.getKey(), productInfo);
        }
        Order order = new Order();
        order.setUserId(Integer.parseInt(userId));
        order.setAddressId(addressId);
        order.setDateOfBooking(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        order.setStatus(0); // Chờ xử lý
        order.setMoney(total);
        OrderServices orderServices = new OrderServices();
        long orderId = orderServices.insertOrderByUser(order, cartMap);
        // Tạo URL thanh toán VNPay
        String vnp_TxnRef = String.valueOf(orderId);
        String vnp_IpAddr = Config.getIpAddress(request);
        String vnp_CreateDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String vnp_ExpireDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis() + 15 * 60 * 1000));

        Map<String, String> vnp_Params = new TreeMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", Config.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf((long) (total * 100))); // Số tiền * 100 (VND)
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang " + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", "billpayment");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        // Tạo chuỗi hashData
        String hashData = Config.hashAllFields(vnp_Params);
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData);
        vnp_Params.put("vnp_SecureHash", vnp_SecureHash);

        // Tạo URL thanh toán
        StringBuilder paymentUrl = new StringBuilder(Config.vnp_PayUrl);
        paymentUrl.append("?");
        try {
            for (Map.Entry<String, String> entry : vnp_Params.entrySet()) {
                paymentUrl.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
                paymentUrl.append("=");
                paymentUrl.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
                paymentUrl.append("&");
            }
            paymentUrl.deleteCharAt(paymentUrl.length() - 1); // Xóa dấu & cuối
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to encode URL parameters", e);
        }


        HttpSession session = request.getSession();
        session.setAttribute("productToRemove", productToRemove.toString());
        // Trả về JSON chứa paymentUrl và orderId
        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("paymentUrl", paymentUrl.toString());
        responseJson.addProperty("orderId", orderId);
        response.getWriter().write(gson.toJson(responseJson));
    }
}