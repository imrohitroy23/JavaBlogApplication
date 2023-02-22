package com.example.demo.Service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.UserService;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User addUser(User user) {
        User saved=this.userRepo.save(user);
        return saved;
    }

    @Override
    public User updateUser(User user, Integer userId) {
    
        User user1=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId)) ;
        user1.setAbout(user.getAbout());
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        User updatedUSer=this.userRepo.save(user1);
        return updatedUSer;
    }

    @Override
    public User getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id" , userId));
        return user;
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        List<User> user=this.userRepo.findAll();
        return user;
    }

    @Override
    public void delUser(Integer userId) {
        // TODO Auto-generated method stub
            
        User user1=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId)) ;
        
        this.userRepo.delete(user1);
    }
    
}
