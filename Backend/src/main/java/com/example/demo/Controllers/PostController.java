package com.example.demo.Controllers;

import com.example.demo.Model.Post;
import com.example.demo.Payloads.ApiResponse;
import com.example.demo.Payloads.PostDto;
import com.example.demo.Service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PostController {
  @Autowired
  private PostService postService;

  @PostMapping("/user/{userId}/category/{categoryId}/posts")
  public ResponseEntity<PostDto> createpost(
    @RequestBody PostDto post,
    @PathVariable Integer userId,
    @PathVariable Integer categoryId
  ) {
    PostDto cr = this.postService.createpost(post, userId, categoryId);
    return new ResponseEntity<PostDto>(cr, HttpStatus.CREATED);
  }

  @GetMapping("/user/{userId}/posts")
  public ResponseEntity<List<PostDto>> getPostsByuser(
    @PathVariable Integer userId
  ) {
    List<PostDto> posts = this.postService.getPostsByUser(userId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping("/category/{categoryId}/posts")
  public ResponseEntity<List<PostDto>> getPostsBycategory(
    @PathVariable Integer categoryId
  ) {
    List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping("/posts")
  public ResponseEntity<List<PostDto>> getPostsAll() {
    List<PostDto> posts = this.postService.getAllPost();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping("/posts/{postId}")
  public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId) {
    PostDto posts = this.postService.getPostById(postId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }


@PutMapping("/posts/{postId}")
public ResponseEntity<PostDto> Update(
 @RequestBody PostDto postDto,
    @PathVariable Integer postId) {
    PostDto posts=this.postService.updatepost(postDto, postId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @DeleteMapping("/posts/{postId}")
  public ApiResponse deletePost(@PathVariable Integer postId)
  {
        this.postService.deletePost(postId);
        return new ApiResponse("Post deleted",true);
  }


}
