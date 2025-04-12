package vn.edu.hcmuaf.fit.projectwebck.services;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.PromotionDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Promotion;

import java.util.List;

public class PromotionServices {
    PromotionDao promotionDao = new PromotionDao();

    public List<Promotion> getAllPromotion() {
        return promotionDao.getAllPromotions();
    }

    public List<Promotion> getPromotionsByUserId(int userId) {
        return promotionDao.getPromotionsByUserId(userId);
    }

    // Lấy thông tin khuyến mãi của user theo ID
    public Integer getPromotionByUser(int userId, int proId) {
        return promotionDao.getPromotionByUser(userId, proId);
    }

    public void updatePromotionByUser(int userId, int proId, int num) {
        promotionDao.updatePromotionByUser(userId, proId, num);
    }

    public void insertPromotion(Promotion promotion) {
        promotionDao.insertPromotion(promotion);
    }

    public void removePromotion(int promotionId) {
        promotionDao.removePromotion(promotionId);
    }
    // Lấy chương trình khuyến mãi theo ID
    public Promotion getPromotionById(int promotionId) {
      return promotionDao.getPromotionById(promotionId);
    }

    public void updatePromotion(Promotion promotion) {
        promotionDao.updatePromotion(promotion);
    }

    public List<Promotion> searchById(int id) {
        return promotionDao.searchById(id);
    }

}
