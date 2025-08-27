package vn.iotstar.services.impl;

import java.sql.Date;

import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;

public class UserServiceImpl implements UserService {

    private final IUserDao userDao = new UserDaoImpl();

    @Override
    public UserModel login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        UserModel user = userDao.findByUserName(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }

    @Override
    public UserModel findByUserName(String username) {
        if (username == null) {
            return null;
        }
        return userDao.findByUserName(username);
    }

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        if (username == null || password == null || email == null || fullname == null || phone == null) {
            return false;
        }

        if (checkExistUsername(username) || checkExistEmail(email) || checkExistPhone(phone)) {
            return false;
        }

        UserModel u = new UserModel();
        u.setUserName(username);
        u.setPassWord(password);
        u.setEmail(email);
        u.setFullName(fullname);
        u.setPhone(phone);
        u.setRoleid(5);
        u.setAvatar(null);
        u.setCreatedDate(new Date(System.currentTimeMillis()));

        insert(u);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public void insert(UserModel u) {
        userDao.insert(u);
    }
}


