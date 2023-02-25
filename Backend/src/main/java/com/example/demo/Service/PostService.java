package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.Post;
import com.example.demo.Payloads.PostDto;

public interface PostService {
    

    PostDto createpost(PostDto postDto,Integer userId, Integer categoryId);

    Post updatepost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    List<Post> getAllPost();

    Post getPostById(Integer postId);

    List<Post> getPostsByCategory(Integer categoryId);

    List<Post> getPostsByUser(Integer userId);

    List<Post> searchPost(String keywords);
    
}
