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


}
