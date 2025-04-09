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
    // Lấy danh sách các mã giảm giá của user theo userId
    public List<Promotion> getPromotionsByUserId(int userId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery(
                        "SELECT p.id, p.name, p.startdate, p.enddate, p.description " +
                                "FROM promotionuser pu " +
                                "INNER JOIN promotions p ON pu.promotionId = p.id " +
                                "WHERE pu.userId = :userId")
                .bind("userId", userId)
                .mapToBean(Promotion.class)
                .list());
    }



    // Lấy thông tin khuyến mãi của user theo ID
    public Integer getPromotionByUser(int userId, int proId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery(
                        "SELECT p.value FROM promotionuser pu INNER JOIN promotions p ON pu.promotionId = p.id WHERE pu.userId = :userId AND pu.promotionId = :proId")
                .bind("userId", userId)
                .bind("proId", proId)
                .mapTo(Integer.class)
                .findOne()
                .orElse(0));  // Trả về 0 nếu không tìm thấy
    }
    public void updatePromotionByUser(int userId, int proId, int num) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE promotionuser SET num = :num WHERE userId = :userId AND promotionId = :proId")
                        .bind("num", num)
                        .bind("userId", userId)
                        .bind("proId", proId)
                        .execute()
        );
    }


    public void insertPromotion(Promotion promotion) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO promotions (name, startDate, endDate, value,description) " +
                        "VALUES (:name, :startDate, :endDate, :value,:description)")
                .bind("name", promotion.getName())
                .bind("startDate", promotion.getStartDate())
                .bind("endDate", promotion.getEndDate())
                .bind("value", promotion.getValue())
                .bind("description", promotion.getDescription())
                .execute());
    }
    // Xóa một chương trình khuyến mãi theo ID
    public void removePromotion(int promotionId) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM promotions WHERE id = :promotionId")
                .bind("promotionId", promotionId)
                .execute());
    }
//    / Cập nhật thông tin chương trình khuyến mãi
    public void updatePromotion(Promotion promotion) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("UPDATE promotions SET name = :name, startDate = :startDate, " +
                        "endDate = :endDate, value = :value,description= :description WHERE id = :promotionId")
                .bind("name", promotion.getName())
                .bind("startDate", promotion.getStartDate())
                .bind("endDate", promotion.getEndDate())
                .bind("value", promotion.getValue())
                .bind("promotionId", promotion.getId())
                .bind("description", promotion.getDescription())
                .execute());
    }
    public List<Promotion> searchById(int id) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM promotions WHERE id LIKE :id")
                .bind("id", "%" + id + "%")
                .mapToBean(Promotion.class)
                .list());
    }


}
