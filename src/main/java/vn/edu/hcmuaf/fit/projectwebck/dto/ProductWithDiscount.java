package vn.edu.hcmuaf.fit.projectwebck.dto;

import vn.edu.hcmuaf.fit.projectwebck.dao.model.DiscoutType;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductImage;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ProductWithDiscount {
    private int id;
    private int prouctId;
    private double price;
    private DiscoutType discoutType;
    private Double discountPercentage;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String nameProduct;
    private List<ProductImage> productImageList;
    public ProductWithDiscount() {
    }

    public ProductWithDiscount(int prouctId, double price, DiscoutType discoutType, Double discountPercentage, LocalDateTime endDate, LocalDateTime startDate) {
        this.prouctId = prouctId;
        this.price = price;
        this.discoutType = discoutType;
        this.discountPercentage = discountPercentage;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public ProductWithDiscount(int productId, DiscoutType discountType, Double discountPercent, Integer discountPrice, LocalDateTime startDateTime, LocalDateTime endDateTime) {
    }

    @Override
    public String toString() {
        return "ProductWithDiscount{" +
                "id=" + id +
                ", prouctId=" + prouctId +
                ", price=" + price +
                ", discoutType=" + discoutType +
                ", discountPercentage=" + discountPercentage +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", nameProduct='" + nameProduct + '\'' +
                '}';
    }

    public List<ProductImage> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<ProductImage> productImageList) {
        this.productImageList = productImageList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProuctId() {
        return prouctId;
    }

    public void setProuctId(int prouctId) {
        this.prouctId = prouctId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DiscoutType getDiscoutType() {
        return discoutType;
    }

    public void setDiscoutType(DiscoutType discoutType) {
        this.discoutType = discoutType;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
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

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
}
