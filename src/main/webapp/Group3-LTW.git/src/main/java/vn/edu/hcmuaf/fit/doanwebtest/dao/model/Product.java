package vn.edu.hcmuaf.fit.doanwebtest.dao.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private double mass;
    private String description;
    private String image;
    private int category;
    private String isNew;

    public Product(String name, int id, double price, double mass,String description, String image, int category, String isNew) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.mass = mass;
        this.description = description;
        this.image = image;
        this.category = category;
        this.isNew = isNew;
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

    @Override
    public String toString() {
        return id + ";" + name + ";" + price + ";" + mass + ";" + description + ";" + image + ";" + category + ";" + isNew;
    }
}
