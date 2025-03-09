package vn.edu.hcmuaf.fit.doanwebtest.services;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.doanwebtest.dao.UserDao;
import vn.edu.hcmuaf.fit.doanwebtest.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.User;

import java.util.List;

public class UserServices {
    UserDao userDao= new UserDao();
    // Lấy tất cả người dùng

    public List<User> getAllUsers() {
       return userDao.getAllUsers();
    }

    // Lấy thông tin người dùng theo ID
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    // Thêm một người dùng mới
    public void insertUser(User user) {
        userDao.insertUser(user);
    }
    public void removeUser(int userId) {
        userDao.removeUser(userId);
    }
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

}
