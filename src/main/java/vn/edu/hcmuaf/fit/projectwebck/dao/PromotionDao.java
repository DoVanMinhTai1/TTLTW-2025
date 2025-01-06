package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Promotion;


import java.util.List;

public class PromotionDao {
    // Lấy tất cả các chương trình khuyến mãi
    public List<Promotion> getAllPromotions() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM promotions")
                .mapToBean(Promotion.class)
                .list());
    }



}
