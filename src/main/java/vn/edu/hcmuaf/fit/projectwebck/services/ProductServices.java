package vn.edu.hcmuaf.fit.projectwebck.services;



import vn.edu.hcmuaf.fit.projectwebck.dao.ProductDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;

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
    public List<Product> searchByName(String name) {
        return productDao.searchByName(name);
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
