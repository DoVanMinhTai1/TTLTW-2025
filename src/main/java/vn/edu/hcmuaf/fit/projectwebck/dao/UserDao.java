package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;

import java.math.BigInteger;
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
    public List<User> searchByName(String name) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM users WHERE fullName LIKE :name")
                .bind("name", "%" + name + "%")
                .mapToBean(User.class)
                .list());
    }

    //login

    public User login(String username, String password) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM users WHERE username = :username AND password = :password")
                .bind("username", username)
                .bind("password", password)
                .mapToBean(User.class)
                .findOne().orElse(null));
    }

    public User findUserByUsername(String username) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM users WHERE username = :username")
                .bind("username", username)
                .mapToBean(User.class)
                .findOne().orElse(null));
    }


    public int register(User user) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO users (username, password, role, fullName, email, dateOfBirth, phone) " +
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


    public String checkPhoneInDatabase(String phone) {
        String dbMessage = null;

        // Tạo kết nối JDBI
        Jdbi jdbi = JDBIConect.get();

        // Sử dụng JDBI để thực hiện truy vấn
        dbMessage = jdbi.withHandle(handle -> {
            Long count = handle.createQuery("SELECT COUNT(*) FROM users WHERE phone = :phone")
                    .bind("phone", phone)
                    .mapTo(Long.class)
                    .one();
            // Kiểm tra nếu số điện thoại không tồn tại
            return count == 0 ? "Số điện thoại không có trong hệ thống." : null;
        });

        return dbMessage; // Nếu số điện thoại tồn tại, trả về null
    }

    public User     getUserByThirtyPartyId(String uId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM users WHERE thirty_party_id = :thirty_party_id")
                .bind("thirty_party_id", uId)
                .mapToBean(User.class)
                .findOne().orElse(null));
    }
}
