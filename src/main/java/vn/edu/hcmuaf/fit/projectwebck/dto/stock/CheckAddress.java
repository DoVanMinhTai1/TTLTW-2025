package vn.edu.hcmuaf.fit.projectwebck.dto.stock;

import java.util.Objects;

public class CheckAddress {
    private String addressLine;
    private String district;
    private String stateOrProvince;
    private String country;

    public CheckAddress(String addressLine, String district, String stateOrProvince, String country) {
        this.addressLine = addressLine;
        this.district = district;
        this.stateOrProvince = stateOrProvince;
        this.country = country;
    }
    public CheckAddress(){

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CheckAddress)) return false;
        CheckAddress that = (CheckAddress) o;
        return Objects.equals(addressLine, that.addressLine) && Objects.equals(district, that.district) && Objects.equals(stateOrProvince, that.stateOrProvince) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLine, district, stateOrProvince, country);
    }

    @Override
    public String toString() {
        return "CheckAddress{" +
                "addressLine='" + addressLine + '\'' +
                ", district='" + district + '\'' +
                ", stateOrProvince='" + stateOrProvince + '\'' +
                ", country='" + country + '\'' +
                '}';
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
}

