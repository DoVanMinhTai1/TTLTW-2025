package vn.edu.hcmuaf.fit.projectwebck.services;


import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import vn.edu.hcmuaf.fit.projectwebck.dao.ProductDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductDiscount;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductImage;
import vn.edu.hcmuaf.fit.projectwebck.dto.product.ProductWithDiscount;

import java.sql.SQLException;
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

    public int insert(Product product) {
        return productDao.insertProduct(product);
    }

    public void insertProductImages(List<String> urls, int productId) {
        productDao.insertProductImages(urls, productId);
    }

    public void removeProduct(int productId) {
        productDao.removeProduct(productId);
    }

    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    //Home
    public List<Product> getBestSellers() {
        return productDao.getBestSellers();
    }

    public List<Product> getAllHome() {
        return productDao.getAllHome();
    }

    //Vegetables
    public List<Product> getAllVegetables() {
        return productDao.getAllVegetables();
    }

    //Tubers
    public List<Product> getAllTubers() {
        return productDao.getAllTubers();
    }

    //Fruits
    public List<Product> getAllFruits() {
        return productDao.getAllFruits();
    }

    public Integer getMass(int id) {
        return productDao.getMass(id);
    }

    //paging
    public static int getTotalProducts() {
        return productDao.getTotalProducts();
    }

    public static int getTotalVegetables() {
        return productDao.getTotalVegetables();
    }

    public List<Product> pagingProduct(int index) {
        return productDao.pagingProduct(index);
    }

    //detail
    public List<String> getDescription(String productId) {
        return productDao.getDescription(productId);
    }

    public List<Product> getRandomRelatedProducts(int productId) {
        return productDao.getRandomRelatedProducts(productId);
    }

    public List<ProductWithDiscount> getProductsWithDiscount() {
        return productDao.getProductDiscountIsActive();
    }

    public ProductDiscount save(ProductDiscount productDiscount) {
        return productDao.save(productDiscount);
    }


    public boolean deleteProductDiscount(int id) {
        return productDao.deleteProductDiscount(id);
    }

    public ProductWithDiscount getProductsWithDiscountById(int productId) {
        return productDao.getProductsWithDiscountById(productId);
    }

    public ProductWithDiscount updateProductWithDiscount(int id, ProductWithDiscount productWithDiscountCons) {
        return productDao.updateProductWithDiscount(id, productWithDiscountCons);
    }

    public List<Product> getByIds(List<Integer> ids) throws SQLException {
        return productDao.getByIds(ids);
    }

    public List<ProductImage> getProductImageByProductId(int id) {
        return productDao.getProductImagesByProductId(id);
    }

    public void deleteProductImages(int productId, int id) {
        productDao.deleteProductImages(productId,id);
    }
}
