package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.PreConnect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static vn.edu.hcmuaf.fit.projectwebck.dao.db.PreConnect.getConnection;

public class CartItemDao {

    public CartItem addCartItem(int userId, int quantity, int productId) throws SQLException {
        String checkQuery = "SELECT * FROM cartitem WHERE userid = ? AND productid = ?";
        String updateQuery = "UPDATE cartitem SET quantity = quantity + ? WHERE productid = ? AND userid = ?";
        String insertQuery = "INSERT INTO cartitem(userid, quantity, productid) VALUES(?, ?, ?)";
        try (Connection conn = getConnection();
        PreparedStatement stmt=conn.prepareStatement(checkQuery) ;) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                try(PreparedStatement stmt2=conn.prepareStatement(updateQuery)) {
                    int currentQuantity = rs.getInt("quantity");
                    stmt2.setInt(1,userId);
                    stmt2.setInt(2,productId);
                    stmt2.setInt(3,currentQuantity + quantity);
                    stmt2.executeUpdate();
                    CartItem cartItem = new CartItem();
                    cartItem.setUserId(userId);
                    cartItem.setProductId(productId);
                    cartItem.setQuantity(quantity);
                    return cartItem;
                }
            } else {
                try(PreparedStatement stmt2=conn.prepareStatement(insertQuery)) {
                    stmt2.setInt(1,userId);
                    stmt2.setInt(2,productId);
                    stmt2.setInt(3,1);
                    stmt2.executeUpdate();
                    CartItem cartItem = new CartItem();
                    cartItem.setUserId(userId);
                    cartItem.setProductId(productId);
                    cartItem.setQuantity(quantity);
                    return cartItem;
                }
            }
        }


    }
    public List<CartItem> getCartItem(int userId) throws SQLException {
        String sql = "SELECT * FROM cartitem WHERE userid = ?";
        List<CartItem> cartItems = new ArrayList<CartItem>();

        try (Connection conn = getConnection();
        PreparedStatement stmt=conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setUserId(userId);
                cartItem.setProductId(rs.getInt("productid"));
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItems.add(cartItem);
            }
            return cartItems;
        }
    }

    public boolean updateCartItem(int userId, int productId, int quantity) throws SQLException {
        String sql = "UPDATE cartitem SET quantity = ? WHERE productid = ? AND userid = ?";
        try (Connection conn = getConnection();
        PreparedStatement stmt=conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);
            stmt.setInt(3, userId);
           int rows =  stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteCartItem(int userId, int productId) throws SQLException {
        String sql = "DELETE FROM cartitem WHERE userid = ? AND productid = ?";
        try (Connection conn = getConnection();
        PreparedStatement stmt=conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            int row = stmt.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
