package com.example.demo.Repository;

import com.example.demo.Model.Category;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepo extends JpaRepository<Post, Integer> {
  List<Post> findByUser(User user);
  List<Post> findByCategory(Category category);
  @Query("select p from Post p where p.title like :key")
  List<Post> searchByTitle(@Param("key") String title);


}
