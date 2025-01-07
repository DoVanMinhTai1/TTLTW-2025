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

}
