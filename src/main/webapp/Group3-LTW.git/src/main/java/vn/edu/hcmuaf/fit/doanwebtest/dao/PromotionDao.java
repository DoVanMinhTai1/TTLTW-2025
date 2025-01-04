package vn.edu.hcmuaf.fit.doanwebtest.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.doanwebtest.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Promotion;

import java.util.List;

public class PromotionDao {
    // Lấy tất cả các chương trình khuyến mãi
    public List<Promotion> getAllPromotions() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM promotions")
                .mapToBean(Promotion.class)
                .list());
    }

    // Lấy thông tin khuyến mãi theo ID
    public Promotion getPromotionById(int id) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM promotions WHERE id = :id")
                .bind("id", id)
                .mapToBean(Promotion.class)
                .findOne().orElse(null));
    }

    // Thêm một chương trình khuyến mãi mới
    public void insertPromotion(Promotion promotion) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO promotions (name, startDate, endDate, value) " +
                        "VALUES (:name, :startDate, :endDate, :value)")
                .bind("name", promotion.getName())
                .bind("startDate", promotion.getStartDate())
                .bind("endDate", promotion.getEndDate())
                .bind("value", promotion.getValue())
                .execute());
    }

    // Xóa một chương trình khuyến mãi theo ID
    public void removePromotion(int promotionId) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM promotions WHERE id = :promotionId")
                .bind("promotionId", promotionId)
                .execute());
    }

    // Cập nhật thông tin chương trình khuyến mãi
    public void updatePromotion(Promotion promotion) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("UPDATE promotions SET name = :name, startDate = :startDate, " +
                        "endDate = :endDate, value = :value WHERE id = :promotionId")
                .bind("name", promotion.getName())
                .bind("startDate", promotion.getStartDate())
                .bind("endDate", promotion.getEndDate())
                .bind("value", promotion.getValue())
                .bind("promotionId", promotion.getId())
                .execute());
    }
}
