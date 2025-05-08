package vn.edu.hcmuaf.fit.projectwebck.dto.product;

import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductDiscount;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductImage;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ProductVariant;

import java.util.List;

public class ProductWithQuantity {
    private int quantity;
    private Product product;

    public ProductWithQuantity(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public ProductWithQuantity() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductWithQuantity{" +
                "quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
