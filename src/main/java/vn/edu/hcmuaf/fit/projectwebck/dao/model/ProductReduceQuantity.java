package vn.edu.hcmuaf.fit.projectwebck.dao.model;

public class ProductReduceQuantity {
    private int productId;
    private int quantity;

    public ProductReduceQuantity(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public ProductReduceQuantity() {
    }


    @Override
    public String toString() {
        return "ProductReduceQuantity{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
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
}
