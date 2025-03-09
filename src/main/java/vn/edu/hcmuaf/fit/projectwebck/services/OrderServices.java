package vn.edu.hcmuaf.fit.projectwebck.services;



import vn.edu.hcmuaf.fit.projectwebck.dao.OrderDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.OrderDetail;

import java.util.List;
import java.util.Map;

public class OrderServices {
    static OrderDao orderDao = new OrderDao();

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
    //Admin, lấy đơn hàng gần đây
    public List<Map<String, Object>> getLatestOrders() {return orderDao.getLatestOrders();}
    //Admin, lấy người dùng mua nhiều nhất trong tháng
    public List<Map<String, Object>> getCustomer() {return orderDao.getCustomer();}
    // Lấy đơn hàng theo ID
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        return orderDao.getOrderDetailsByOrderId(orderId);
    }

    // Xóa một đơn hàng theo ID
    public void removeOrder(int orderId) {
        orderDao.removeOrder(orderId);
    }
    public List<Order> searchById(int id) {
        return orderDao.searchById(id);
    }
    public List<Order> getOrderByUserId(int userId) {
        return orderDao.getOrderByUserId(userId);
    }
    public long insertOrderByUser(Order order, Map<Integer, Map<String, Double>> cartMap) {
        return orderDao.insertOrderByUser(order, cartMap);
    }
}
