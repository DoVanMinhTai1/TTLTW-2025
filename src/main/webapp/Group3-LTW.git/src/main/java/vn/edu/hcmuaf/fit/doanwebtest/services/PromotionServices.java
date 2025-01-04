package vn.edu.hcmuaf.fit.doanwebtest.services;

import vn.edu.hcmuaf.fit.doanwebtest.dao.PromotionDao;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Promotion;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.User;

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

    public void updatePromotion(Promotion promotion) {
        promotionDao.updatePromotion(promotion);
    }

    public void removePromotion(int promotionId) {
        promotionDao.removePromotion(promotionId);
    }
}
