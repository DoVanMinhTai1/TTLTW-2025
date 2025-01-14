package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.OrderDetail;

import java.util.List;

public class OrderDetailDao {
    public List<OrderDetail> getAllOrderDetails() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM orderdetail")
                .mapToBean(OrderDetail.class)
                .list());
    }
}
