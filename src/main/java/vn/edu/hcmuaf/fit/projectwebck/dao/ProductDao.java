package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDao {
    static Map<Integer, Product> data = new HashMap<>();

    public List<Product> getAll() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("select * from products")
                .mapToBean(Product.class)
                .list());
    }

    public Product getById(int id) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("select * from products where id = :id")
                .bind("id", id)
                .mapToBean(Product.class)
                .findOne().orElse(null));
    }
    public void insertProduct(Product product) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> {
            // Cập nhật câu lệnh SQL để lưu tất cả các thông tin của sản phẩm
            handle.createUpdate("INSERT INTO products (name, price, mass, description, category, image) " +
                            "VALUES (:name, :price, :mass, :describe, :category, :image)")
                    .bind("name", product.getName())
                    .bind("price", product.getPrice())
                    .bind("mass", product.getMass())
                    .bind("describe", product.getDescription())
                    .bind("category", product.getCategory())
                    .bind("image", product.getImage()) // Lưu đường dẫn ảnh
                    .execute();
        });
    }
    public void removeProduct(int productId) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> {
            handle.createUpdate("DELETE FROM products WHERE id = :productId")
                    .bind("productId", productId)
                    .execute();
        });
    }
    public void updateProduct(Product product) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> {
            handle.createUpdate("UPDATE products SET name = :name, price = :price, mass = :mass, " +
                            "description = :describe, category = :category, image = :image " +
                            "WHERE id = :productId")
                    .bind("name", product.getName())
                    .bind("price", product.getPrice())
                    .bind("mass", product.getMass())
                    .bind("describe", product.getDescription())
                    .bind("category", product.getCategory())
                    .bind("image", product.getImage()) // Cập nhật đường dẫn ảnh
                    .bind("productId", product.getId()) // Điều kiện cập nhật
                    .execute();
        });
    }
    public List<Product> searchByName(String name) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM products WHERE name LIKE :name")
                .bind("name", "%" + name + "%")
                .mapToBean(Product.class)
                .list());
    }


}
