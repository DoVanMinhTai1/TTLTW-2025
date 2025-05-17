package vn.edu.hcmuaf.fit.projectwebck.services;

import vn.edu.hcmuaf.fit.projectwebck.dao.StockDao;
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

    public Stock findById(int productId) {
        return stockDao.findById(productId);
    }

    public Stock findStockByKey(StockKey key) {
        return stockDao.findBy(key);
    }
}
