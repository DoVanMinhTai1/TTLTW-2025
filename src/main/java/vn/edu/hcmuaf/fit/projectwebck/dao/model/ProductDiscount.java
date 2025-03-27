package vn.edu.hcmuaf.fit.projectwebck.dao.model;

import java.util.Date;

public class ProductDiscount {
    private int id;
    private int productId;
    private Double discoutPrice;
    private Double discountPercentage;
    private DiscoutType discountType;
    private Date startDate;
    private Date endDate;
    private boolean isActive;

    public ProductDiscount(int id, int productId, Double discoutPrice, Double discountPercentage, DiscoutType discountType, Date startDate, Date endDate, boolean isActive) {
        this.id = id;
        this.productId = productId;
        this.discoutPrice = discoutPrice;
        this.discountPercentage = discountPercentage;
        this.discountType = discountType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }
    public ProductDiscount() {

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

    public Double getDiscoutPrice() {
        return discoutPrice;
    }

    public void setDiscoutPrice(Double discoutPrice) {
        this.discoutPrice = discoutPrice;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public DiscoutType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscoutType discountType) {
        this.discountType = discountType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "ProductDiscount{" +
                "id=" + id +
                ", productId=" + productId +
                ", discoutPrice=" + discoutPrice +
                ", discountPercentage=" + discountPercentage +
                ", discountType=" + discountType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                '}';
    }
}
