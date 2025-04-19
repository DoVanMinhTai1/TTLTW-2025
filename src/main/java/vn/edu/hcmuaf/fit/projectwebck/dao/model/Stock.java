package vn.edu.hcmuaf.fit.projectwebck.dao.model;

public class Stock {
    private int id;
    private int productId;
    private int quantity;
    private String name;
    private String addressLine;
    private String  district;
    private String stateOrProvince;
    private String  country;

    public Stock(int id,int productId, int quantity, String name, String addressLine, String district, String stateOrProvince, String country) {
        this.id = id;
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



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", addressLine='" + addressLine + '\'' +
                ", district='" + district + '\'' +
                ", stateOrProvince='" + stateOrProvince + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
