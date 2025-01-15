package vn.edu.hcmuaf.fit.projectwebck.dao.cart;

import java.io.Serializable;

public class CartProduct implements Serializable {
    private int id;
    private String name;
    private double price;
    private String img;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartProduct(int id, String title, double price, String img, int quantity) {
        this.id = id;
        this.name = title;
        this.price = price;
        this.img = img;
        this.quantity = quantity;
    }

    public CartProduct() {
    }


}
