package vn.edu.hcmuaf.fit.doanwebtest.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.doanwebtest.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Order;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.OrderDetail;

import java.util.List;

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
}
