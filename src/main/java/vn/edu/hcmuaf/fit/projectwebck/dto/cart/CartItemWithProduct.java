package vn.edu.hcmuaf.fit.projectwebck.dto.cart;

import vn.edu.hcmuaf.fit.projectwebck.dao.model.CartItem;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;

import java.util.List;

public class CartItemWithProduct {
    private int productId;
    private String name;
    private double price;
    private double mass;
    private String description;
    private String image;
    private int categoryId;
    private String userId;
    private int quantity;

    public CartItemWithProduct(int productId, String name, double price, double mass, String description, String image, int categoryId, String userId, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.mass = mass;
        this.description = description;
        this.image = image;
        this.categoryId = categoryId;
        this.userId = userId;
        this.quantity = quantity;
    }

    public CartItemWithProduct() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemWithProduct{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", mass=" + mass +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", categoryId=" + categoryId +
                ", userId='" + userId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

