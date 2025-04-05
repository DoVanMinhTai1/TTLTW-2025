package vn.edu.hcmuaf.fit.projectwebck.services;



import vn.edu.hcmuaf.fit.projectwebck.dao.ProductDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductDiscount;
import vn.edu.hcmuaf.fit.projectwebck.dto.ProductWithDiscount;

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

    //Home
    public List<Product> getBestSellers() {return productDao.getBestSellers();}
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
    public static int getTotalProducts() { return productDao.getTotalProducts(); }

    public static int getTotalVegetables() { return productDao.getTotalVegetables(); }

    public List<Product> pagingProduct(int index) { return productDao.pagingProduct(index); }

    //detail
    public List<String> getDescription(String productId){return productDao.getDescription(productId);}
    public List<Product> getRandomRelatedProducts(int productId) {return productDao.getRandomRelatedProducts(productId);}
    public List<ProductWithDiscount> getProductsWithDiscount() {
        return productDao.getProductDiscountIsActive();
    }

    public ProductDiscount save(ProductDiscount productDiscount) {
       return  productDao.save(productDiscount);
    }


    public boolean deleteProductDiscount(int id) {
        return productDao.deleteProductDiscount(id);
    }

    public ProductWithDiscount getProductsWithDiscountById(int productId) {
        return productDao.getProductsWithDiscountById(productId);
    }
}
