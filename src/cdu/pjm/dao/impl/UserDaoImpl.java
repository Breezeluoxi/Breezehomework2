package cdu.pjm.dao.impl;

import cdu.pjm.dao.UserDao;
import cdu.pjm.model.User;
import cdu.pjm.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUser(User user) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement =JDBCUtils.getConnection().prepareStatement("insert into user values(?,?,?,?,?,?,?)");
        setUserInfo(user, statement);
        statement.executeUpdate();
        JDBCUtils.close(connection,statement);
    }

    @Override
    public int getUserCount() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("select count(1) from user");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        JDBCUtils.close(connection,statement,resultSet);
        return count;
    }

    @Override
    public List<User> getUserList(int start, int num) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from user limit ?,?");
        statement.setInt(1,start);
        statement.setInt(2,num);
        ResultSet resultSet = statement.executeQuery();
        List<User> list=new ArrayList<>();
        while (resultSet.next()){
            User user=new User();
            getUserInfo(resultSet, user);
            list.add(user);
        }
        JDBCUtils.close(connection,statement,resultSet);
        return list;
    }

    @Override
    public int deleteUser(int userId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from user where userID=?");
        statement.setInt(1,userId);
        int i = statement.executeUpdate();
        JDBCUtils.close(connection,statement);
        return i;
    }

    @Override
    public User queryUser(Long id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from user where userID=?");
        statement.setLong(1,id);
        ResultSet resultSet = statement.executeQuery();
        User user=new User();
        while (resultSet.next()){
            getUserInfo(resultSet, user);
        }
        JDBCUtils.close(connection,statement,resultSet);
        return user;
    }

    @Override
    public int updateUser(User user) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("update user set userName=?,userPwd=?,userGrender=?,userAge=?,UserLike=?,userPicUrl=? where userID=?");
        setUserInfo(user, statement);
        statement.setLong(7,user.getUserID());
        int i= statement.executeUpdate();
        JDBCUtils.close(connection,statement);
        return i;
    }
    private void getUserInfo(ResultSet resultSet, User user) throws SQLException {
        user.setUserID(resultSet.getLong(1));
        user.setUserName(resultSet.getString(2));
        user.setUserPwd(resultSet.getString(3));
        user.setUserGender(resultSet.getString(4));
        user.setUserAge(resultSet.getString(5));
        user.setUserLike(resultSet.getString(6));
        user.setUserPicUrl(resultSet.getString(7));
    }
    private void setUserInfo(User user, PreparedStatement statement) throws SQLException {
        statement.setLong(1,user.getUserID());
        statement.setString(2,user.getUserName());
        statement.setString(3,user.getUserPwd());
        statement.setString(4,user.getUserGender());
        statement.setString(5,user.getUserAge());
        statement.setString(6,user.getUserLike());
        statement.setString(7,user.getUserPicUrl());
    }
}
