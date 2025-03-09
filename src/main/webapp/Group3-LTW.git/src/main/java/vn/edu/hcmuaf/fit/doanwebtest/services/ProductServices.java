package vn.edu.hcmuaf.fit.doanwebtest.services;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.doanwebtest.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanwebtest.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Product;

import java.util.List;

public class ProductServices {
    static ProductDao productDao = new ProductDao();

    public List<Product> getAll() {
        return productDao.getAll();
    }
    public Product getById(int id) {
       return productDao.getById(id);
    }
    public Product getDetail(String in) {
        try {
            int id = Integer.parseInt(in);
            return productDao.getById(id);

        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void insert(Product product) {
        productDao.insertProduct(product);
    }
    public void removeProduct(int productId) {
       productDao.removeProduct(productId);
    }
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }
}
