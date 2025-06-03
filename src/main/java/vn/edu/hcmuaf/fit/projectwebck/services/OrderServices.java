package vn.edu.hcmuaf.fit.projectwebck.services;


import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.OrderDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Order;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.OrderDetail;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductReduceQuantity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServices {
    static OrderDao orderDao = new OrderDao();
    static StockService stockService = new StockService();
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    //Admin, lấy đơn hàng gần đây
    public List<Map<String, Object>> getLatestOrders() {
        return orderDao.getLatestOrders();
    }

    //Admin, lấy người dùng mua nhiều nhất trong tháng
    public List<Map<String, Object>> getCustomer() {
        return orderDao.getCustomer();
    }

    //Lay danh sach tai khoan sap xep theo thu tu giam dan cua tong tien
    public List<Map<String, Object>> getListOfAccounts() {
        return orderDao.getListOfAccounts();
    }

    // Lấy đơn hàng theo ID
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        return orderDao.getOrderDetailsByOrderId(orderId);
    }

    // Xóa một đơn hàng theo ID
    public void removeOrder(int orderId) {
        orderDao.removeOrder(orderId);
    }

    public Order getOrderById(int orderId) {
        return orderDao.getOrderById(orderId);
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

    public boolean updateOrderStatus(int orderId, int status) {
        return orderDao.updateOrderStatus(orderId, status);
    }

    public void reduceStockWhenOrderConfirmed(int orderId) {
        List<OrderDetail> details = orderDao.getOrderDetailsByOrderId(orderId); // You need to implement this method
        List<ProductReduceQuantity> productReduceQuantities = new ArrayList<ProductReduceQuantity>();
        for (OrderDetail detail : details) {
            productReduceQuantities.add(new ProductReduceQuantity(detail.getProductId(), detail.getQuantity()));
        }
        stockService.reduceQuantityByProductIds(productReduceQuantities);
    }

    public void increateStockWhenOrderReturn(int orderId) {
        List<OrderDetail> details = orderDao.getOrderDetailsByOrderId(orderId); // You need to implement this method
        List<ProductReduceQuantity> productReduceQuantities = new ArrayList<ProductReduceQuantity>();
        for (OrderDetail detail : details) {
            productReduceQuantities.add(new ProductReduceQuantity(detail.getProductId(), detail.getQuantity()));
        }
        stockService.increateQuantityByProductIds(productReduceQuantities);
    }





}
