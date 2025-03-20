package vn.edu.hcmuaf.fit.projectwebck.dao.model;

public class ProductVariant {
    private int id;
    private int massValue;
    private String massUnits;
    private double price;
    private int productId;

    public ProductVariant(int id, int massValue, String massUnits, double price, int productId) {
        this.id = id;
        this.massValue = massValue;
        this.massUnits = massUnits;
        this.price = price;
        this.productId = productId;
    }

    public ProductVariant() {

    }

    @Override
    public String toString() {
        return "ProductVariant{" +
                "id=" + id +
                ", massValue=" + massValue +
                ", massUnits='" + massUnits + '\'' +
                ", price=" + price +
                ", productId=" + productId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMassValue() {
        return massValue;
    }

    public void setMassValue(int massValue) {
        this.massValue = massValue;
    }

    public String getMassUnits() {
        return massUnits;
    }

    public void setMassUnits(String massUnits) {
        this.massUnits = massUnits;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
