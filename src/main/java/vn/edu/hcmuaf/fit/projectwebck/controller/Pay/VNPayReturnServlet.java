    package vn.edu.hcmuaf.fit.projectwebck.controller.Pay;

    import com.google.gson.Gson;
    import com.google.gson.reflect.TypeToken;
    import jakarta.servlet.*;
    import jakarta.servlet.http.*;
    import jakarta.servlet.annotation.*;
    import vn.edu.hcmuaf.fit.projectwebck.services.CartItemService;
    import vn.edu.hcmuaf.fit.projectwebck.services.OrderServices;

    import java.io.IOException;
    import java.lang.reflect.Type;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    @WebServlet(name = "VNPayReturnServlet", value = "/vnpay_return")
    public class VNPayReturnServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Map<String, String> vnp_Params = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                vnp_Params.put(entry.getKey(), entry.getValue()[0]);
            }

            String vnp_SecureHash = vnp_Params.remove("vnp_SecureHash");
            String signValue = Config.hashAllFields(vnp_Params);
            String calculatedHash = Config.hmacSHA512(Config.secretKey, signValue);

            String vnp_ResponseCode = vnp_Params.get("vnp_ResponseCode");
            String vnp_TxnRef = vnp_Params.get("vnp_TxnRef"); // orderId

            if (calculatedHash.equals(vnp_SecureHash)) {
                if ("00".equals(vnp_ResponseCode)) {
                    // Thanh toán thành công
                    OrderServices orderServices = new OrderServices();
                    orderServices.updateOrderStatus(Integer.parseInt(vnp_TxnRef), 1); // Cập nhật trạng thái đơn hàng

                    // Lấy lại thông tin sản phẩm cần xóa
                    HttpSession session = request.getSession();
                    String productJson = (String) session.getAttribute("productToRemove");
                    String userId = (String) session.getAttribute("userId");

                    if (productJson != null && userId != null) {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<Map<String, Integer>>>(){}.getType();
                        List<Map<String, Integer>> productList = gson.fromJson(productJson, listType);

                        List<Integer> productIds = new ArrayList<>();
                        for (Map<String, Integer> item : productList) {
                            productIds.add(item.get("productId"));
                        }

                        CartItemService cartItemService = new CartItemService();
                        cartItemService.deleteCartItem(Integer.parseInt(userId), productIds);
                    }


                    session.removeAttribute("productToRemove");
                    session.removeAttribute("userId");

                    request.setAttribute("orderId", vnp_TxnRef);
                    request.setAttribute("message", "Thanh toán thành công!");
                    request.getRequestDispatcher("/jsp/success.jsp").forward(request, response);
                } else {
                    // Thanh toán thất bại
                    OrderServices orderServices = new OrderServices();
                    orderServices.removeOrder(Integer.parseInt(vnp_TxnRef)); // Xóa đơn hàng
                    request.setAttribute("message", "Thanh toán thất bại. Mã lỗi: " + vnp_ResponseCode);
                    request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
                }
            } else {
                // Chữ ký không hợp lệ
                request.setAttribute("message", "Chữ ký không hợp lệ.");
                request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        }
    }