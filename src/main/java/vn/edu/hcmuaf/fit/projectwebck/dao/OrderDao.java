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
        return jdbi.withHandle(handle -> handle.createQuery("SELECT o.id, o.userId, o.dateOfBooking, o.status, o.money, o.addressId, u.fullName FROM orders o INNER JOIN users u ON o.userId = u.id")
                .mapToBean(Order.class)
                .list());
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
                int quantity = productInfo.get("quantity") != null ? productInfo.get("quantity").intValue() : 0;
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

}
