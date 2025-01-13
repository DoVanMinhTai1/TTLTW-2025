package vn.edu.hcmuaf.fit.projectwebck.services;



import vn.edu.hcmuaf.fit.projectwebck.dao.UserDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;

import java.util.List;

public class UserServices {
    UserDao userDao= new UserDao();
    // Lấy tất cả người dùng

    public List<User> getAllUsers() {
       return userDao.getAllUsers();
    }
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
    public void insertUser(User user) {
        userDao.insertUser(user);
    }
    public void removeUser(int userId) {
        userDao.removeUser(userId);
    }
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
    public void updatePassword(User user) {
        userDao.updatePassword(user);
    }
    public List<User> searchByName(String name) {
        return userDao.searchByName(name);
    }
    public User login(String username, String password) { return userDao.login(username, password); }
    public int register(User user) { return userDao.register(user); }
    public User findUserByUsername(String username) {return userDao.findUserByUsername(username);}
}
