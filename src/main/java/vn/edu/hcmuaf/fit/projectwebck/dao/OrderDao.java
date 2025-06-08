package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.OrderDetail;

import java.util.List;
import java.util.Map;

public class OrderDao {
    // Lấy tất cả các đơn hàng
    public List<Order> getAllOrders() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT o.id, o.userId, o.dateOfBooking, o.status, o.money, o.addressId, " +
                        "u.fullName,u.phone, a.address " +
                        "FROM orders o " +
                        "INNER JOIN users u ON o.userId = u.id " +
                        "INNER JOIN address a ON o.addressId = a.id")
                .mapToBean(Order.class)
                .list());
    }

    //Lấy đơn hàng gần nhất
    public List<Map<String, Object>> getLatestOrders() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT u.username, p.name, od.totalamount FROM orders o JOIN orderdetail od ON " +
                                "o.id = od.orderId JOIN products p ON p.id = od.productId JOIN users u ON u.id = o.userId " +
                                "ORDER BY o.dateOfBooking " +
                                "DESC LIMIT 7")
                        .mapToMap() // Ánh xạ kết quả thành Map
                        .list()
        );
    }

    //lấy người dùng mua nhiều trong tháng
    public List<Map<String, Object>> getCustomer() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("select u.username, count(o.userId) as SOLANMUAHANG from orders o join users u on o.userId = u.id\n" +
                                "where MONTH(o.dateOfBooking) = MONTH(CURRENT_DATE()) and YEAR(o.dateOfBooking) = YEAR(CURRENT_DATE())\n" +
                                "group by u.username\n" +
                                "order by SOLANMUAHANG desc\n" +
                                "limit 4")
                        .mapToMap() // Ánh xạ kết quả thành Map
                        .list()
        );
    }

    //Lay danh sach tai khoan sap xep theo thu tu giam dan cua tong tien
    public List<Map<String, Object>> getListOfAccounts() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery(
                        "SELECT u.id , u.username, " +
                                "COALESCE(SUM(o.money), 0) AS tong " +
                                "FROM users u " +
                                "LEFT JOIN orders o ON u.id = o.userId " +
                                "GROUP BY u.id, u.username " +
                                "ORDER BY tong DESC"
                ).mapToMap().list()
        );
    }


    //     Lấy đơn hàng theo ID
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT od.id,od.productId, od.quantity,od.totalAmount,od.orderId,p.image, p.name , p.price FROM orderdetail od INNER JOIN products p ON od.productId = p.id WHERE od.orderId = :orderId")
                        .bind("orderId", orderId)
                        .mapToBean(OrderDetail.class)
                        .list()
        );
    }

    // Xóa một đơn hàng theo ID
    public void removeOrder(int orderId) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM orders WHERE id = :orderId")
                .bind("orderId", orderId)
                .execute());
    }

    public Order getOrderById(int orderId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM orders WHERE id = :orderId")
                        .bind("orderId", orderId)
                        .mapToBean(Order.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public List<Order> searchById(int id) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT o.id, o.userId, o.dateOfBooking, o.status, o.money, o.addressId, u.fullName FROM orders o INNER JOIN users u ON o.userId = u.id WHERE o.id LIKE :id")
                .bind("id", "%" + id + "%")
                .mapToBean(Order.class)
                .list());
    }

    //    Lấy tất cả đơn hàng của một userId
    public List<Order> getOrderByUserId(int userId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT o.id, o.userId, o.dateOfBooking, o.status, o.money, o.addressId, a.address FROM orders o INNER JOIN address a ON o.addressId = a.id WHERE o.userId = :userId")
                        .bind("userId", userId)
                        .mapToBean(Order.class)
                        .list()
        );
    }

//    public long insertOrderByUser(Order order, Map<Integer, Map<String, Double>> cartMap) {
//        Jdbi jdbi = JDBIConect.get();
//        return jdbi.withHandle(handle -> {
//            // 1. Cập nhật câu lệnh SQL để lưu thông tin đơn hàng
//            handle.createUpdate("INSERT INTO orders (userId, dateOfBooking, status, money, addressId) " +
//                            "VALUES (:userId, :dateOfBooking, :status, :money, :addressId)")
//                    .bind("userId", order.getUserId())
//                    .bind("dateOfBooking", order.getDateOfBooking())
//                    .bind("status", order.getStatus())
//                    .bind("money", order.getMoney())
//                    .bind("addressId", order.getAddressId())
//                    .execute();
//
//            // 2. Lấy ID của đơn hàng vừa chèn (orderId)
//            String getOrderIdQuery = "SELECT id FROM orders ORDER BY id DESC LIMIT 1";
//            long orderId = handle.createQuery(getOrderIdQuery)
//                    .mapTo(Long.class)
//                    .one();
//
//            // 3. Thêm chi tiết đơn hàng vào bảng orderdetail
//            String orderDetailQuery = "INSERT INTO orderdetail (productId, quantity, totalamount, orderId) " +
//                    "VALUES (:productId, :quantity, :totalamount, :orderId)";
//            String updateProductMass = "UPDATE products SET  mass= mass - :quantity WHERE id = :productId";
//            for (Map.Entry<Integer, Map<String, Double>> entry : cartMap.entrySet()) {
//                int productId = entry.getKey();
//                Map<String, Double> productInfo = entry.getValue();
//                int quantity = productInfo.get("quantity") != null ? productInfo.get("quantity").intValue() : 0;
//                double price = productInfo.get("price") != null ? productInfo.get("price") : 0.0;
//
//                handle.createUpdate(orderDetailQuery)
//                        .bind("productId", productId)
//                        .bind("quantity", quantity)
//                        .bind("totalamount", price * quantity)
//                        .bind("orderId", orderId)
//                        .execute();
//                // Cập nhật số lượng sản phẩm trong bảng products
//                handle.createUpdate(updateProductMass)
//                        .bind("quantity", quantity)
//                        .bind("productId", productId)
//                        .execute();
//            }
//
//            // Trả về orderId
//            return orderId;
//        });
//    }
//
//

    public long insertOrderByUser(Order order, Map<Integer, Map<String, Double>> cartMap) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> {
            // 1. Cập nhật câu lệnh SQL để lưu thông tin đơn hàng
            handle.createUpdate("INSERT INTO orders (userId, dateOfBooking, status, money, addressId) " +
                            "VALUES (:userId, :dateOfBooking, :status, :money, :addressId)")
                    .bind("userId", order.getUserId())
                    .bind("dateOfBooking", order.getDateOfBooking())
                    .bind("status", order.getStatus())
                    .bind("money", order.getMoney())
                    .bind("addressId", order.getAddressId())
                    .execute();

            // 2. Lấy ID của đơn hàng vừa chèn (orderId)
            String getOrderIdQuery = "SELECT id FROM orders ORDER BY id DESC LIMIT 1";
            long orderId = handle.createQuery(getOrderIdQuery)
                    .mapTo(Long.class)
                    .one();

            // 3. Thêm chi tiết đơn hàng vào bảng orderdetail
            String orderDetailQuery = "INSERT INTO orderdetail (productId, quantity, totalamount, orderId) " +
                    "VALUES (:productId, :quantity, :totalamount, :orderId)";
            String updateProductMass = "UPDATE products SET  mass= mass - :quantity WHERE id = :productId";
            for (Map.Entry<Integer, Map<String, Double>> entry : cartMap.entrySet()) {
                int productId = entry.getKey();
                Map<String, Double> productInfo = entry.getValue();
//                int quantity = productInfo.get("quantity") != null ? productInfo.get("quantity").intValue() : 0;
                int quantity;
                Object quantityObj = productInfo.get("quantity");
                if (quantityObj instanceof String) {
                    try {
                        quantity = Integer.parseInt((String) quantityObj);
                    } catch (NumberFormatException e) {
                        quantity = 0; // Hoặc xử lý lỗi phù hợp (ví dụ: throw exception hoặc ghi log)
                    }
                } else if (quantityObj instanceof Double) {
                    quantity = ((Double) quantityObj).intValue();
                } else {
                    quantity = 0; // Giá trị mặc định nếu không phải String hoặc Double
                }
                double price = productInfo.get("price") != null ? productInfo.get("price") : 0.0;

                handle.createUpdate(orderDetailQuery)
                        .bind("productId", productId)
                        .bind("quantity", quantity)
                        .bind("totalamount", price * quantity)
                        .bind("orderId", orderId)
                        .execute();
                // Cập nhật số lượng sản phẩm trong bảng products
                handle.createUpdate(updateProductMass)
                        .bind("quantity", quantity)
                        .bind("productId", productId)
                        .execute();
            }

            // Trả về orderId
            return orderId;
        });
    }


    public boolean updateOrderStatus(int orderId, int status) {
        Jdbi jdbi = JDBIConect.get();
        int rowsAffected = jdbi.withHandle(handle -> handle.createUpdate("UPDATE orders SET status = :status WHERE id = :orderId")
                .bind("status", status)
                .bind("orderId", orderId)
                .execute());

        // Kiểm tra nếu có ít nhất một bản ghi bị ảnh hưởng
        return rowsAffected > 0;
    }




}
