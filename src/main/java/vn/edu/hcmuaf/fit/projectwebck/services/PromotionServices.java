package vn.edu.hcmuaf.fit.projectwebck.services;



import vn.edu.hcmuaf.fit.projectwebck.dao.PromotionDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Promotion;

import java.util.List;

public class PromotionServices {
    PromotionDao promotionDao = new PromotionDao();

    public List<Promotion> getAllPromotion() {
        return promotionDao.getAllPromotions();
    }

    public Promotion getPromotionById(int id) {
        return promotionDao.getPromotionById(id);
    }
    public void insertPromotion(Promotion promotion) {
        promotionDao.insertPromotion(promotion);
    }
    public void removePromotion(int promotionId) {
        promotionDao.removePromotion(promotionId);
    }
    public void updatePromotion(Promotion promotion) {
        promotionDao.updatePromotion(promotion);
    }

}
