package vn.edu.hcmuaf.fit.projectwebck.controller.Pay;

import com.google.gson.*;
import io.leangen.geantyref.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.cart.Cart;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.*;
import vn.edu.hcmuaf.fit.projectwebck.dto.product.ProductWithQuantity;
import vn.edu.hcmuaf.fit.projectwebck.services.AddressServices;
import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;
import vn.edu.hcmuaf.fit.projectwebck.services.StockService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "AddOrder", value = "/addOrder")
public class AddOrder extends HttpServlet {
    private static final BigInteger MAX_LONG = new BigInteger(String.valueOf(Long.MAX_VALUE));

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                public JsonElement serialize(LocalDate src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(src.toString()); // or format with DateTimeFormatter
                }
            })
            .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                public JsonElement serialize(LocalDateTime src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(src.toString());
                }
            })
            .create();

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
                order.setUserId(Integer.valueOf(userId));
                OrderServices service = new OrderServices();

//  Issue 47: Xử lý số lượng sản phẩm khi đặt hàng
                ProductServices productServices = new ProductServices();

                List<Integer> productIds = new ArrayList<>();
                Map<Integer, Integer> requestedQuantities = new HashMap<>();

                for (Map.Entry<Integer, Map<String, Double>> entry : cartMap.entrySet()) {
                    Map<String, Double> productInfo = entry.getValue();

                    int productId = entry.getKey();
                    int quantity;

                    try {
                        quantity = productInfo.get("quantity") != null
                                ? (int) Double.parseDouble(productInfo.get("quantity").toString())
                                : 0;
                    } catch (NullPointerException | NumberFormatException e) {
                        quantity = 0;
                    }


                    productIds.add(productId);
                    requestedQuantities.put(productId, quantity);
                }

                StockService stockService = new StockService();
                List<Stock> listStockByProductIds = new ArrayList<>();
                try {
                    listStockByProductIds = stockService.findAllByProductIds(productIds);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

//  Exception nếu sản phẩm chưa có trong kho
                Set<Integer> foundIds = listStockByProductIds.stream().map(Stock::getProductId).collect(Collectors.toSet());

                List<Integer> missingIds = productIds.stream().filter(productId
                        -> !foundIds.contains(productId)).collect(Collectors.toList());
                if (!missingIds.isEmpty()) {

                    try {
                        List<Product> productNotFoundInStock = productServices.getByIds(missingIds);
                        response.setContentType("application/json");
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write(gson.toJson(productNotFoundInStock));
                        return;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

//  Kết thúc Exception
                List<Integer> productIdsOutQuantity = new ArrayList<>();
//  Tạo List Object chứa productId và quantity để trừ số lượng khi đặt hàng thành công
                List<ProductReduceQuantity> productReduceQuantities = new ArrayList<>();

                List<ProductWithQuantity> productsWithQuantity = new ArrayList<>();

                for (Stock stock : listStockByProductIds) {
                    Integer productId = stock.getProductId();
                    Integer requested = requestedQuantities.getOrDefault(productId.intValue(), 0);

                    if (requested <= 0 || requested > stock.getQuantity()) {
//                Add product Id với số lượng không phù hợp vào List
                        productIdsOutQuantity.add(stock.getProductId());

                        try {
                            List<Product> products = productServices.getByIds(productIdsOutQuantity);
                            for (Product product : products) {
                                productsWithQuantity.add(new ProductWithQuantity(stock.getQuantity(), product));
                            }
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
                    response.getWriter().write(gson.toJson(productsWithQuantity));
                    return;
                }

                long orderId = -1;
                try {
                    orderId = service.insertOrderByUser(order, cartMap);
                    response.setContentType("application/json");
                    response.getWriter().write(gson.toJson(orderId));
                } catch (Exception e) {
                    e.printStackTrace(); // Or better: log to a logger

                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("{\"error\": \"Database error occurred while inserting order.\"}");
                }
            } else {
                int addressId = jsonObject.get("addressId").getAsInt();

                double total = jsonObject.get("total").getAsDouble();
                System.out.println(jsonObject.get("total"));

                System.out.println("123" + total);


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
                ProductServices productServices = new ProductServices();

                List<Integer> productIds = new ArrayList<>();
                Map<Integer, Integer> requestedQuantities = new HashMap<>();

                for (Map.Entry<Integer, Map<String, Double>> entry : cartMap.entrySet()) {
                    Map<String, Double> productInfo = entry.getValue();

                    int productId = entry.getKey();
                    int quantity;

                    try {
                        quantity = productInfo.get("quantity") != null
                                ? (int) Double.parseDouble(productInfo.get("quantity").toString())
                                : 0;
                    } catch (NullPointerException | NumberFormatException e) {
                        quantity = 0;
                    }


                    productIds.add(productId);
                    requestedQuantities.put(productId, quantity);
                }

                StockService stockService = new StockService();
                List<Stock> listStockByProductIds = new ArrayList<>();
                try {
                    listStockByProductIds = stockService.findAllByProductIds(productIds);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

//  Exception nếu sản phẩm chưa có trong kho
                Set<Integer> foundIds = listStockByProductIds.stream().map(Stock::getProductId).collect(Collectors.toSet());

                List<Integer> missingIds = productIds.stream().filter(productId
                        -> !foundIds.contains(productId)).collect(Collectors.toList());
                if (!missingIds.isEmpty()) {

                    try {
                        List<Product> productNotFoundInStock = productServices.getByIds(missingIds);
                        response.setContentType("application/json");
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write(gson.toJson(productNotFoundInStock));
                        return;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

//  Kết thúc Exception
                List<Integer> productIdsOutQuantity = new ArrayList<>();
//  Tạo List Object chứa productId và quantity để trừ số lượng khi đặt hàng thành công
                List<ProductReduceQuantity> productReduceQuantities = new ArrayList<>();

                List<ProductWithQuantity> productsWithQuantity = new ArrayList<>();

                for (Stock stock : listStockByProductIds) {
                    Integer productId = stock.getProductId();
                    Integer requested = requestedQuantities.getOrDefault(productId.intValue(), 0);

                    if (requested <= 0 || requested > stock.getQuantity()) {
//                Add product Id với số lượng không phù hợp vào List
                        productIdsOutQuantity.add(stock.getProductId());

                        try {
                            List<Product> products = productServices.getByIds(productIdsOutQuantity);
                            for (Product product : products) {
                                productsWithQuantity.add(new ProductWithQuantity(stock.getQuantity(), product));
                            }
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
                    response.getWriter().write(gson.toJson(productsWithQuantity));
                    return;
                }

                long orderId = -1;
                try {
                    orderId = service.insertOrderByUser(order, cartMap);
                    response.setContentType("application/json");
                    response.getWriter().write(gson.toJson(orderId));
                } catch (Exception e) {
                    e.printStackTrace(); // Or better: log to a logger

                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("{\"error\": \"Database error occurred while inserting order.\"}");
                }



            }
        }

    }

}