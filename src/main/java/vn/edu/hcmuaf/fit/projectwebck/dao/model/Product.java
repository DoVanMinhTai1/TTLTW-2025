package vn.edu.hcmuaf.fit.projectwebck.dao.model;
import java.util.List;
public class Product {
    private int id;
    private String name;
    private double price;
    private double mass;
    private String description;
    private String image;
    private int category;
    private String isNew;
    private List<ProductImage> productImages;
    private List<ProductVariant> productVariants;

    public Product(int id, String name, double price, double mass, String description, String image, int category, String isNew, List<ProductImage> productImages, List<ProductVariant> productVariants) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mass = mass;
        this.description = description;
        this.image = image;
        this.category = category;
        this.isNew = isNew;
        this.productImages = productImages;
        this.productVariants = productVariants;
    }

    public Product(String name, int id, double price, double mass, String description, String image, int category, String isNew) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.mass = mass;
        this.description = description;
        this.image = image;
        this.category = category;
        this.isNew = isNew;
    }

    public Product(int id, String name, double price, double mass, String description, String image, int category, String isNew, List<ProductImage> productImages) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mass = mass;
        this.description = description;
        this.image = image;
        this.category = category;
        this.isNew = isNew;
        this.productImages = productImages;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getMass() {
        return mass;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getCategory() {
        return category;
    }

    public String getIsNew() {
        return isNew;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public List<ProductVariant> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(List<ProductVariant> productVariants) {
        this.productVariants = productVariants;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", mass=" + mass +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", category=" + category +
                ", isNew='" + isNew + '\'' +
                ", productImages=" + productImages +
                ", productVariants=" + productVariants +
                '}';
    }
}
