package vn.edu.hcmuaf.fit.doanwebtest.services;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.doanwebtest.dao.OrderDao;
import vn.edu.hcmuaf.fit.doanwebtest.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanwebtest.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Order;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.OrderDetail;

import java.util.List;

public class OrderServices {
    static OrderDao orderDao = new OrderDao();

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    // Lấy đơn hàng theo ID
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        return orderDao.getOrderDetailsByOrderId(orderId);
    }

    // Xóa một đơn hàng theo ID
    public void removeOrder(int orderId) {
        orderDao.removeOrder(orderId);
    }
}
