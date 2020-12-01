package cdu.pjm.service;



import cdu.pjm.model.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public int getUserCount();
    public List<User> getUserList(int start,int num);
    public int deleteUser(int userId);
    public User queryUser(Long id);
    public int updateUser(User user);
}
