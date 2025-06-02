package vn.edu.hcmuaf.fit.projectwebck.dao;


import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductReduceQuantity;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Promotion;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Stock;
import vn.edu.hcmuaf.fit.projectwebck.dto.stock.StockKey;

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

    public Stock findByProductId(int productId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM stocks WHERE productId = :productId")
                        .bind("productId", productId)
                        .mapToBean(Stock.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public Stock findById(int Id) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM stocks WHERE id = :id")
                        .bind("id", Id)
                        .mapToBean(Stock.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public Stock findBy(StockKey key) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM stocks WHERE productId = :productId " +
                                "AND addressLine = :addressLine " +
                                "AND district = :district " +
                                "AND stateOrProvince = :stateOrProvince " +
                                "AND Country = :country")
                        .bind("productId", key.getProductId())
                        .bind("addressLine", key.getAddressLine())
                        .bind("district", key.getDistrict())
                        .bind("stateOrProvince", key.getStateOrProvince())
                        .bind("country", key.getCountry())
                        .mapToBean(Stock.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public List<Stock> findAllByProductIds(List<Integer> productIds) {
        String sql = "SELECT * FROM stocks WHERE productId IN (<productIds>)";
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bindList("productIds", productIds)
                        .mapToBean(Stock.class)
                        .list());
    }

    public void reduceQuantityByProductIds(List<ProductReduceQuantity> productReduceQuantities) {
        String sql = "UPDATE stocks SET quantity = quantity - :quantity WHERE productId = :productId";
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> {
            for (ProductReduceQuantity productReduceQuantity : productReduceQuantities) {
                handle.createUpdate(sql).bind("quantity", productReduceQuantity.getQuantity())
                        .bind("productId", productReduceQuantity.getProductId()).execute();
            }
        });
    }

    public void increateQuantityByProductIds(List<ProductReduceQuantity> productReduceQuantities) {
        String sql = "UPDATE stocks SET quantity = quantity + :quantity WHERE productId = :productId";
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> {
            for (ProductReduceQuantity productReduceQuantity : productReduceQuantities) {
                handle.createUpdate(sql).bind("quantity", productReduceQuantity.getQuantity())
                        .bind("productId", productReduceQuantity.getProductId()).execute();
            }
        });
    }

    public static void main(String[] args) {
        StockDao stockDao = new StockDao();
        System.out.println(stockDao.getAllStocks());

    }
}
