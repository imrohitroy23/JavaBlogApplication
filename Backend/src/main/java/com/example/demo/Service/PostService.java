package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.Post;
import com.example.demo.Payloads.PostDto;

public interface PostService {
    

    PostDto createpost(PostDto postDto,Integer userId, Integer categoryId);

    PostDto updatepost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getAllPost();

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPost(String keywords);
    
}
