package vn.edu.hcmuaf.fit.projectwebck.dao.model;

public class ProductImage {
    private int id;
    private String url;
    private int productId;

    public ProductImage(int id, String url, int productId) {
        this.id = id;
        this.url = url;
        this.productId = productId;
    }

    public ProductImage() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", productId=" + productId +
                '}';
    }
}
