package com.example.demo.Service;


import com.example.demo.Payloads.PostDto;
import com.example.demo.Payloads.PostResponse;

import java.util.List;

public interface PostService {
  PostDto createpost(PostDto postDto, Integer userId, Integer categoryId);

  PostDto updatepost(PostDto postDto, Integer postId);

  void deletePost(Integer postId);

  PostResponse getAllPost( Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

  PostDto getPostById(Integer postId);

  List<PostDto> getPostsByCategory(Integer categoryId);

  List<PostDto> getPostsByUser(Integer userId);

  List<PostDto> searchPost(String keywords);
}
