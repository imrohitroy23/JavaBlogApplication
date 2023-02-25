package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.User;

import com.example.demo.Model.Category;
import com.example.demo.Model.Post;

public interface PostRepo extends JpaRepository<Post,Integer> {
 
    public List<Post> findByCategory(Category category); 
    public List<Post> findByUser(User user);
}
