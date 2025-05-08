package vn.edu.hcmuaf.fit.projectwebck.dao;


import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Promotion;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Stock;

import java.util.List;

public class StockDao {
    public List<Stock> getAllStocks() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM stocks")
                .mapToBean(Stock.class)
                .list());
    }

    public boolean updateStock(Stock stock) {
        Jdbi jdbi = JDBIConect.get();
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE stocks SET productId = :productId, quantity = :quantity, name = :name, addressLine = :addressLine, district = :district, stateOrProvince = :stateOrProvince, country = :country WHERE id = :id")
                        .bind("id", stock.getId())
                        .bind("productId", stock.getProductId())
                        .bind("quantity", stock.getQuantity())
                        .bind("name", stock.getName())
                        .bind("addressLine", stock.getAddressLine())
                        .bind("district", stock.getDistrict())
                        .bind("stateOrProvince", stock.getStateOrProvince())
                        .bind("country", stock.getCountry())
                        .execute()
        );
        return rowsAffected > 0;
    }


    public void insertStock(List<Stock> stocks) {
        Jdbi jdbi = JDBIConect.get();
        String sql = " INSERT INTO stocks (productId, quantity, name, addressLine, district, stateOrProvince, country)   VALUES (:productId, :quantity, :name, :addressLine, :district, :stateOrProvince, :country)";
        jdbi.useHandle(handle -> {
                    for (Stock stock : stocks) {
                        handle.createUpdate(sql)
                                .bind("productId", stock.getProductId())
                                .bind("quantity", stock.getQuantity())
                                .bind("name", stock.getName())
                                .bind("addressLine", stock.getAddressLine())
                                .bind("district", stock.getDistrict())
                                .bind("stateOrProvince", stock.getStateOrProvince())
                                .bind("country", stock.getCountry())
                                .execute();
                    }
                }
        );
    }

    public boolean removeStock(int stockId) {
        Jdbi jdbi = JDBIConect.get();
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM stocks WHERE id = :stockId")
                        .bind("stockId", stockId)
                        .execute()
        );
        return rowsAffected > 0;
    }


}
