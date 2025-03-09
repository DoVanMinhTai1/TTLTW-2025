package vn.edu.hcmuaf.fit.projectwebck.services;

import vn.edu.hcmuaf.fit.projectwebck.dao.OrderDetailDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.OrderDetail;


import java.util.List;
import java.util.Map;

public class OrderDetailServices {
    static OrderDetailDao orderDetailDao = new OrderDetailDao();
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailDao.getAllOrderDetails();
    }
}
