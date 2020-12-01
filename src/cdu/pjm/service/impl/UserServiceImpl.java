package cdu.pjm.service.impl;



import cdu.pjm.dao.impl.UserDaoImpl;
import cdu.pjm.model.User;
import cdu.pjm.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoImpl userDao= new UserDaoImpl();

    @Override
    public void addUser(User user) {
        try {
            userDao.addUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int getUserCount() {
        try {
            return userDao.getUserCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<User> getUserList(int start,int num) {
        List<User> userList=null;
        try {
             userList= userDao.getUserList(start,num);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public int deleteUser(int userId) {
        int i=0;
        try {
            i=userDao.deleteUser(userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public User queryUser(Long id) {
        User user=null;
        try {
            user= userDao.queryUser(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public int updateUser(User user) {
        int i =-1;
        try {
            i=userDao.updateUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }
}
