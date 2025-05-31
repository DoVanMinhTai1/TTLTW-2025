package vn.edu.hcmuaf.fit.projectwebck.controller.Pay;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.leangen.geantyref.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.cart.Cart;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.*;
import vn.edu.hcmuaf.fit.projectwebck.services.AddressServices;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;
import vn.edu.hcmuaf.fit.projectwebck.services.StockService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddOrder", value = "/addOrder")
public class AddOrder extends HttpServlet {
    private static final BigInteger MAX_LONG = new BigInteger(String.valueOf(Long.MAX_VALUE));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getContentType());
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            System.out.println(line);
            sb.append(line);
        }

        System.out.println(request);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
        JsonElement userIdElement = jsonObject.get("userId");
        if (userIdElement.isJsonPrimitive()) {
            String userId = jsonObject.get("userId").getAsString();
            BigInteger userIdBigInteger = new BigInteger(userId);
            if (userIdBigInteger.longValue() > 10000) {
                userId = userId;
                int addressId = jsonObject.get("addressId").getAsInt();
                double total = jsonObject.get("total").getAsDouble();


                AddressServices addressServices = new AddressServices();
                Address address = addressServices.getByThirtyPartyId(userId);
                String[] parts = address.getAddress().split(",");
                String province = parts.length > 3 ? parts[3].trim() : "";
                if (!province.equals("Hồ Chí Minh")) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // Đặt mã trạng thái HTTP lỗi
                    JsonObject errorResponse = new JsonObject();
                    errorResponse.addProperty("message", "Chỉ hỗ trợ đặt hàng tại Hồ Chí Minh.");
                    response.getWriter().write(gson.toJson(errorResponse));
                    return;
                }

// Chuyển JSON thành Map
                Type cartType = new TypeToken<Map<Integer, Map<String, Double>>>() {
                }.getType();
                Map<Integer, Map<String, Double>> cartMap = gson.fromJson(jsonObject.get("cartMap"), cartType);

// Tạo đối tượng Order
                Order order = new Order();
                order.setThirty_party_id(userId);
                order.setAddressId(addressId);
                order.setDateOfBooking(LocalDate.now().toString());
                order.setStatus(0);
                order.setMoney(total);
                OrderServices service = new OrderServices();
                long orderId = service.insertOrderByUser(order, cartMap);
                HttpSession session = request.getSession(true);
                Cart cart = (Cart) session.getAttribute("cart");
                cart.removeAll();
                session.setAttribute("cart", cart);
// Phản hồi kết quả
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(orderId));
            } else {
                int addressId = jsonObject.get("addressId").getAsInt();
                double total = jsonObject.get("total").getAsDouble();

                int userIdInt = jsonObject.get("userId").getAsInt();
                AddressServices addressServices = new AddressServices();
                Address address = addressServices.getByIdOrigin(userIdInt);
                String[] parts = address.getAddress().split(",");
                String province = parts.length > 3 ? parts[3].trim() : "";
                if (!province.equals("Hồ Chí Minh")) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // Đặt mã trạng thái HTTP lỗi
                    JsonObject errorResponse = new JsonObject();
                    errorResponse.addProperty("message", "Chỉ hỗ trợ đặt hàng tại Hồ Chí Minh.");
                    response.getWriter().write(gson.toJson(errorResponse));
                    return;
                }

// Chuyển JSON thành Map
                Type cartType = new TypeToken<Map<Integer, Map<String, Double>>>() {
                }.getType();
                Map<Integer, Map<String, Double>> cartMap = gson.fromJson(jsonObject.get("cartMap"), cartType);


// Tạo đối tượng Order
                Order order = new Order();
                order.setUserId(userIdInt);
                order.setAddressId(addressId);
                order.setDateOfBooking(LocalDate.now().toString());
                order.setStatus(0);
                order.setMoney(total);
                OrderServices service = new OrderServices();

//  Issue 47: Xử lý số lượng sản phẩm khi đặt hàng
                List<Integer> productIds = new ArrayList<>();
                Map<Integer, Integer> requestedQuantities = new HashMap<>();

                for (Map.Entry<Integer, Map<String, Double>> entry : cartMap.entrySet()) {
                    Map<String, Double> productInfo = entry.getValue();

                    int productId = entry.getKey();
                    int quantity = productInfo.get("quantity") != null ? (int) Double.parseDouble(productInfo.get("quantity").toString()) : 0;

                    productIds.add(productId);
                    requestedQuantities.put(productId, quantity);
                }

                StockService stockService = new StockService();
                List<Stock> listStockByProductIds = stockService.findAllByProductIds(productIds);

                List<Integer> productIdsOutQuantity = new ArrayList<>();
//  Tạo List Object chứa productId và quantity để trừ số lượng khi đặt hàng thành công
                List<ProductReduceQuantity> productReduceQuantities = new ArrayList<>();

                List<Product> products = new ArrayList<>();

                for (Stock stock : listStockByProductIds) {
                    Integer productId = stock.getProductId();
                    Integer requested = requestedQuantities.getOrDefault(productId.intValue(), 0);

                    if (requested <= 0 || requested > stock.getQuantity()) {
//                Add product Id với số lượng không phù hợp vào List
                        productIdsOutQuantity.add(stock.getProductId());
                        ProductServices productServices = new ProductServices();

                        try {
                            products = productServices.getByIds(productIdsOutQuantity);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        productReduceQuantities.add(new ProductReduceQuantity(stock.getProductId(), requested));
                    }
                }
// Phản hồi kết quả
                if (!productIdsOutQuantity.isEmpty()) {
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write(gson.toJson(products));
                    return;
                }

                long orderId = service.insertOrderByUser(order, cartMap);
//                    stockService.reduceQuantityByProductIds(productReduceQuantities);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(orderId));


            }
        }

    }

}