package vn.edu.hcmuaf.fit.projectwebck.services;

import vn.edu.hcmuaf.fit.projectwebck.dao.StockDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Stock;

import java.util.List;

public class StockService {
    private StockDao stockDao;

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
}
