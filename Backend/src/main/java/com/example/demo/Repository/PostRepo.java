package com.example.demo.Repository;

import com.example.demo.Model.Category;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
  List<Post> findByUser(User user);
  List<Post> findByCategory(Category category);
}
