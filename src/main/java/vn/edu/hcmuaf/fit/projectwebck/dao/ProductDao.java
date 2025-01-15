package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;


import java.text.SimpleDateFormat;
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



    //Home
    public List<Product> getBestSellers() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery(
                "select p.image, p.category, p.name, p.price, count(od.id) as SLB " +
                        "from products p join  orderdetail od on p.id = od.productID " +
                        "join orders o on od.orderId = o.id " +
                        "where o.dateOfBooking = CURRENT_DATE() " +
                        "group by p.image, p.category, p.name, p.price " +
                        "order by SLB desc "+
                        "limit 8")
                .mapToBean(Product.class)
                .list());
    }

    public List<Product> getAllHome() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("select * from products limit 40")
                .mapToBean(Product.class)
                .list());
    }

    //Vegetables
    public List<Product> getAllVegetables() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("select * from products where category = 1")
                .mapToBean(Product.class)
                .list());
    }

    //Tuber
    public List<Product> getAllTubers() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("select * from products where category = 2")
                .mapToBean(Product.class)
                .list());
    }

    //Fruit
    public List<Product> getAllFruits() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("select * from products where category = 3")
                .mapToBean(Product.class)
                .list());
    }
    //paging
    public static int getTotalProducts() {
        Jdbi jdbi = JDBIConect.get();
        try {
            return jdbi.withHandle(handle ->
                    handle.createQuery("SELECT count(*) FROM products")
                            .mapTo(Integer.class)
                            .one()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static int getTotalVegetables() {
        Jdbi jdbi = JDBIConect.get();
        try {
            return jdbi.withHandle(handle ->
                    handle.createQuery("SELECT count(*) FROM products where category = 1")
                            .mapTo(Integer.class)
                            .one()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Product> pagingProduct(int index) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM products ORDER BY id LIMIT 50 OFFSET :offset")
                        .bind("offset", (index - 1) * 50) // Tính toán offset
                        .mapToBean(Product.class) // Ánh xạ kết quả vào đối tượng Product
                        .list()
        );
    }

    //Detail
    public List<String> getDescription(String productId) {
        Jdbi jdbi = JDBIConect.get();

        // Lấy danh sách mô tả từ cơ sở dữ liệu
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT description FROM products WHERE id = :productId")
                        .bind("productId", productId)
                        .mapTo(String.class)
                        .list()
        );
    }

    public List<Product> getRandomRelatedProducts(int productId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM products " +
                                "WHERE category = (SELECT category FROM products WHERE id = :id) " +
                                "AND id <> :id " +
                                "ORDER BY RAND() " + // Sử dụng ORDER BY RAND() để lấy ngẫu nhiên
                                "LIMIT 5")
                        .bind("id", productId)
                        .map((rs, ctx) -> {
                            Product product = new Product();
                            product.setId(rs.getInt("id")); // Thay đổi theo tên cột trong bảng
                            product.setName(rs.getString("name")); // Thay đổi theo tên cột trong bảng
                            product.setPrice(rs.getDouble("price"));
                            product.setCategory(rs.getInt("category")); // Thay đổi theo tên cột trong bảng
                            product.setImage(rs.getString("image"));
                            // Thiết lập các thuộc tính khác của Product nếu có
                            return product;
                        })
                        .list()
        );
    }
}
