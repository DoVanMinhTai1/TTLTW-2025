package vn.edu.hcmuaf.fit.projectwebck.dao.model;

import java.time.LocalDateTime;
import java.util.Date;

public class ProductDiscount {
    private int id;
    private int productId;
    private Double discountPrice;
    private Double discountPercentage;
    private DiscoutType discountType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isActive;

    public ProductDiscount(int id, int productId, Double discountPrice, Double discountPercentage, DiscoutType discountType, LocalDateTime startDate, LocalDateTime endDate, boolean isActive) {
        this.id = id;
        this.productId = productId;
        this.discountPrice = discountPrice;
        this.discountPercentage = discountPercentage;
        this.discountType = discountType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }
    public ProductDiscount() {

    }

    public ProductDiscount(Integer productId, DiscoutType discountType, Double discountPercent, Integer discountPrice, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.productId = productId;
        this.discountType = discountType;  // Chuyển chuỗi thành Enum
        this.discountPercentage = discountPercent;
        this.discountPrice = discountPrice != null ? discountPrice.doubleValue() : null;
        this.startDate = startDateTime;
        this.endDate = endDateTime;
        this.isActive = true;
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


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ProductDiscount{" +
                "id=" + id +
                ", productId=" + productId +
                ", discountPrice=" + discountPrice +
                ", discountPercentage=" + discountPercentage +
                ", discountType=" + discountType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                '}';
    }
}
