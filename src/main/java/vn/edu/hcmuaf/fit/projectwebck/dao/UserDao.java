package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;

import java.util.List;

public class UserDao {
    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM users")
                .mapToBean(User.class)
                .list());
    }
    // Lấy thông tin người dùng theo ID
    public User getUserById(int id) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM users WHERE id = :id")
                .bind("id", id)
                .mapToBean(User.class)
                .findOne().orElse(null));
    }
    // Thêm một người dùng mới
    public void insertUser(User user) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO users (username, password, role, fullName, email, dateOfBirth, phone) " +
                        "VALUES (:username, :password, :decentralization, :fullName, :email, :dateOfBirth, :phone)")
                .bind("username", user.getUsername())
                .bind("password", user.getPassword())
                .bind("decentralization", user.getRole())
                .bind("fullName", user.getFullName())
                .bind("email", user.getEmail())
                .bind("dateOfBirth", user.getDateOfBirth())
                .bind("phone", user.getPhone())
                .execute());
    }
    // Xóa một người dùng theo ID
    public void removeUser(int userId) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM users WHERE id = :userId")
                .bind("userId", userId)
                .execute());
    }
    // Cập nhật thông tin người dùng
    public void updateUser(User user) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("UPDATE users SET username = :username, password = :password, " +
                        "role = :role, fullName = :fullName, email = :email, " +
                        "dateOfBirth = :dateOfBirth, phone = :phone WHERE id = :userId")
                .bind("username", user.getUsername())
                .bind("password", user.getPassword())
                .bind("role", user.getRole())
                .bind("fullName", user.getFullName())
                .bind("email", user.getEmail())
                .bind("dateOfBirth", user.getDateOfBirth())
                .bind("phone", user.getPhone())
                .bind("userId", user.getId())
                .execute());
    }
    public void updatePassword(User user) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("UPDATE users SET  password = :password WHERE id = :id ")
                .bind("password", user.getPassword())
                .bind("id", user.getId())
                .execute());
    }
}
