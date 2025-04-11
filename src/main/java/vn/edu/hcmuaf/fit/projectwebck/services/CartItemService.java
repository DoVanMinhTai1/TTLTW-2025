package vn.edu.hcmuaf.fit.projectwebck.services;

import vn.edu.hcmuaf.fit.projectwebck.dao.CartItemDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.CartItem;

import java.sql.SQLException;
import java.util.List;

public class CartItemService {
    private  CartItemDao cartItemDao;

    public CartItemService() {
    }

    public CartItem addCartItem(int userId, int quantity,int productId) throws SQLException {
        return cartItemDao.addCartItem(userId, quantity, productId);
    }
    public List<CartItem> getCartItems(int userId) throws SQLException {
        return cartItemDao.getCartItem(userId);
    }
    public boolean updateCartItem(int userId, int productId, int quantity) throws SQLException {
      return  cartItemDao.updateCartItem(userId, productId, quantity);
    }
    public boolean deleteCartItem(int userId, int productId) throws SQLException {
       return cartItemDao.deleteCartItem(userId, productId);
    }
}

