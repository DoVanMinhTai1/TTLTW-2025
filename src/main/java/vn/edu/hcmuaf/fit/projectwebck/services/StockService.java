package vn.edu.hcmuaf.fit.projectwebck.services;

import vn.edu.hcmuaf.fit.projectwebck.dao.StockDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductReduceQuantity;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Stock;
import vn.edu.hcmuaf.fit.projectwebck.dto.stock.StockKey;

import java.util.List;

public class StockService {
    private StockDao stockDao = new StockDao();

    public void addStock(List<Stock> stock) {
        stockDao.insertStock(stock);
    }

    public boolean deleteStockById(int stockId) {
        return stockDao.removeStock(stockId);
    }

    public List<Stock> getAllStocks() {
        return stockDao.getAllStocks();
    }

    public boolean updateStock(Stock stockToUpdate) {
        return stockDao.updateStock(stockToUpdate);
    }

    public boolean updateStockByProductId(Stock stockToUpdate) {
        return stockDao.updateStock(stockToUpdate);
    }

    public Stock findByProductId(int productId) {
        return stockDao.findById(productId);
    }

    public Stock findStockByKey(StockKey key) {
        return stockDao.findBy(key);
    }

    public List<Stock> findAllByProductIds(List<Integer> productIds) {
        return stockDao.findAllByProductIds(productIds);
    }

    public void reduceQuantityByProductIds(List<ProductReduceQuantity> productReduceQuantities) {
         stockDao.reduceQuantityByProductIds(productReduceQuantities);
    }

    public void increateQuantityByProductIds(List<ProductReduceQuantity> productReduceQuantities) {
        stockDao.increateQuantityByProductIds(productReduceQuantities);
    }

    public Stock getStockById(int id) {
        return stockDao.findById(id);
    }
}
