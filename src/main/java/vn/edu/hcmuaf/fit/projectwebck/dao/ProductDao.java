package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.*;
import vn.edu.hcmuaf.fit.projectwebck.dto.ProductWithDiscount;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ProductDao {
    static Map<Integer, Product> data = new HashMap<>();

    public List<Product> getAll() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("select * from products order by id desc")
                .mapToBean(Product.class)
                .list());
    }

    //    public Product getById(int id) {
//        Jdbi jdbi = JDBIConect.get();
//        return jdbi.withHandle(handle -> handle.createQuery("select * from products where id = :id")
//                .bind("id", id)
//                .mapToBean(Product.class)
//                .findOne().orElse(null));
//    }
    public Product getById(int id) {
        Jdbi jdbi = JDBIConect.get();
        Product products = jdbi.withHandle(handle -> handle.createQuery("  select p.id,p.name,p.price,p.mass,p.description,p.image, p.category,p.extraDay" +
                        " from products p " +
                        "where p.id = :id")
                .bind("id", id)
                .map(
                        rs -> {
                            Product product = new Product();
                            product.setId(rs.getColumn("id", Integer.class));
                            product.setName(rs.getColumn("name", String.class));
                            product.setPrice(rs.getColumn("price", Double.class));
                            product.setMass(rs.getColumn("mass", Double.class));
                            product.setDescription(rs.getColumn("description", String.class));
                            product.setImage(rs.getColumn("image", String.class));
                            product.setCategory(rs.getColumn("category", Integer.class));
                            return product;
                        })
                .findOne().orElse(null));
        products.setProductImages(getProductImagesByProductId(products.getId()));
        products.setProductVariants(getSizeByProductId(products.getId()));
        return products;

    }

    public List<ProductVariant> getSizeByProductId(int productId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle
                .createQuery("SELECT pv.id as pvId, pv.mass_value as pvValue,pv.mass_unit as pvUnit,pv.price as pvPrice,pv.productId as pvIdProduct\n" +
                        "FROM productvariants pv\n" +
                        "WHERE pv.productId = :productId")
                .bind("productId", productId)
                .map(rs -> {
                    ProductVariant productSize = new ProductVariant();
                    productSize.setId(rs.getColumn("pvId", Integer.class));
                    productSize.setMassValue(rs.getColumn("pvValue", Integer.class));
                    productSize.setMassUnits(rs.getColumn("pvUnit", String.class));
                    productSize.setProductId(rs.getColumn("pvIdProduct", Integer.class));
                    productSize.setPrice(rs.getColumn("pvPrice", Double.class));
                    return productSize;
                }).list());
    }

    public List<ProductImage> getProductImagesByProductId(int productId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle
                .createQuery("SELECT pi.id as piId, pi.url as piUrl, pi.productId as piProductId\n" +
                        "FROM productimages pi\n" +
                        "WHERE pi.productId = :productId")
                .bind("productId", productId)
                .map(rs -> {
                    ProductImage productImage = new ProductImage();
                    productImage.setId(rs.getColumn("piId", Integer.class));
                    productImage.setProductId(rs.getColumn("piProductId", Integer.class));
                    productImage.setUrl(rs.getColumn("piUrl", String.class));
                    return productImage;
                }).list());
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
                        "select p.id, p.image, p.category, p.name, p.price, count(od.id) as SLB " +
                                "from products p join orderdetail od on p.id = od.productID " +
                                "join orders o on od.orderId = o.id " +
                                "where o.dateOfBooking = CURRENT_DATE() " +
                                "group by p.id, p.image, p.category, p.name, p.price " +
                                "order by SLB desc " +
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

    public Integer getMass(int id) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT mass FROM products WHERE id = :id")
                .bind("id", id)
                .mapTo(Integer.class)
                .one());
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
//    sản phẩm đang giảm giá
    public List<ProductWithDiscount> getProductDiscountIsActive() {
        Jdbi jdbi = JDBIConect.get();
        String sql = "select pd.id,pd.product_id,pd.discount_price,pd.discoun_type,pd.percentage_discount,pd.startdatetime,pd.enddatetime,p.`name`\n" +
                "from products p\n" +
                "INNER JOIN productdiscounts pd ON p.id = pd.product_id\n" +
                "WHERE pd.is_active = TRUE AND NOW() BETWEEN pd.startdatetime AND pd.enddatetime\n" +
                "ORDER BY pd.id asc;";
        List<ProductWithDiscount> product =  jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .map((rs) -> {
                            ProductWithDiscount productWithDiscount = new ProductWithDiscount();
                            productWithDiscount.setId(rs.getColumn("id",Integer.class));
                            productWithDiscount.setProuctId(rs.getColumn("product_id",Integer.class));
                            productWithDiscount.setPrice(rs.getColumn("discount_price",Double.class));
                            productWithDiscount.setDiscoutType(rs.getColumn("discoun_type", DiscoutType.class));
                            productWithDiscount.setDiscountPercentage(rs.getColumn("percentage_discount",Double.class));
                            productWithDiscount.setStartDate(rs.getColumn("startdatetime",LocalDateTime.class));
                            productWithDiscount.setEndDate(rs.getColumn("enddatetime", LocalDateTime.class));
                            productWithDiscount.setNameProduct(rs.getColumn("name",String.class));
                            return productWithDiscount;
                        }).list());
        System.out.println(product);
        product.forEach(item -> item.setProductImageList(getProductImagesByProductId(item.getProuctId())));
        return product;
    }

}
