package vn.edu.hcmuaf.fit.projectwebck.dao.cart;



import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class Cart {
    Map<Integer, CartProduct> data = new HashMap<>();

    public boolean add(Product product) {
        if (data.containsKey(product.getId())) {
            update(product.getId(), data.get(product.getId()).getQuantity() + 1);
            return true;
        }
        data.put(product.getId(), convert(product));
        return false;
    }

    public boolean update(int id, int quantity) {
        if (!data.containsKey(id) || quantity < 1) return false;
        CartProduct cartProduct = data.get(id);
        cartProduct.setQuantity(quantity);
        data.put(id, cartProduct);
        return true;
    }

    public boolean remove(int id) {
        if (!data.containsKey(id)) return false;
        data.remove(id);
        return true;
    }
    public void removeAll() {
        data.clear();
    }

    public List<CartProduct> getList() {
        return new ArrayList<>(data.values());
    }

    public int getTotalQuantity() {
        AtomicInteger totalQuantity = new AtomicInteger(0);
        data.values().stream().forEach(cartProduct -> totalQuantity.addAndGet(cartProduct.getQuantity()));
        return totalQuantity.get();
    }

    public Double getTotal() {
        return data.values().stream()
                .mapToDouble(cartProduct -> cartProduct.getQuantity() * cartProduct.getPrice())
                .sum();
    }


    public CartProduct convert(Product p) {
        CartProduct cp = new CartProduct();
        cp.setId(p.getId());
        cp.setName(p.getName());
        cp.setPrice(p.getPrice());
        cp.setImg(p.getImage());
        cp.setQuantity(1);
        return cp;
    }
}
