package vn.edu.hcmuaf.fit.projectwebck.dto.stock;

import java.util.Objects;

public class StockKey {
    private int productId;
    private String addressLine;
    private String district;
    private String stateOrProvince;
    private String country;

    public StockKey(int productId, String addressLine, String district, String stateOrProvince, String country) {
        this.productId = productId;
        this.addressLine = addressLine;
        this.district = district;
        this.stateOrProvince = stateOrProvince;
        this.country = country;
    }

    public StockKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StockKey)) return false;
        StockKey stockKey = (StockKey) o;
        return productId == stockKey.productId && Objects.equals(addressLine, stockKey.addressLine) && Objects.equals(district, stockKey.district) && Objects.equals(stateOrProvince, stockKey.stateOrProvince) && Objects.equals(country, stockKey.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, addressLine, district, stateOrProvince, country);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
        return "StockKey{" +
                "productId=" + productId +
                ", addressLine='" + addressLine + '\'' +
                ", district='" + district + '\'' +
                ", stateOrProvince='" + stateOrProvince + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
