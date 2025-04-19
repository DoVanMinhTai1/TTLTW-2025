package vn.edu.hcmuaf.fit.projectwebck.dao.model;

public class Stock {
    private int productId;
    private int quantity;
    private String name;
    private String addressLine;
    private String  district;
    private String stateOrProvince;
    private String  country;

    public Stock(int productId, int quantity, String name, String addressLine, String district, String stateOrProvince, String country) {
        this.productId = productId;
        this.quantity = quantity;
        this.name = name;
        this.addressLine = addressLine;
        this.district = district;
        this.stateOrProvince = stateOrProvince;
        this.country = country;
    }

    public Stock() {
    }
}
