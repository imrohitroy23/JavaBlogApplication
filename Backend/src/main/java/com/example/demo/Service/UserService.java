package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.User;

public interface UserService {
    public User addUser(User user);
    public User updateUser(User user, Integer userId);
    public User getUserById(Integer userId);
    public List<User> getAll();
    public void delUser(Integer userId);
}
