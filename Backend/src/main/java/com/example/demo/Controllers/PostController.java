package com.example.demo.Controllers;

import com.example.demo.Configuration.AppConstansts;
import com.example.demo.Model.Post;
import com.example.demo.Payloads.ApiResponse;
import com.example.demo.Payloads.PostDto;
import com.example.demo.Payloads.PostResponse;
import com.example.demo.Service.FileService;
import com.example.demo.Service.PostService;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class PostController {
  @Autowired
  private PostService postService;

  @Autowired
  private FileService fileService;

  @Value("${project.image}")
  private String path;

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
  public ResponseEntity<PostResponse> getPostsAll(
    @RequestParam(
      value = "pageNumber",
      defaultValue = AppConstansts.PAGE_NUMBER,
      required = false
    ) Integer pageNumber,
    @RequestParam(
      value = "pageSize",
      defaultValue = AppConstansts.PAGE_SIZE,
      required = false
    ) Integer pageSize,
    @RequestParam(
      value = "sortBy",
      defaultValue = AppConstansts.SORT_BY,
      required = false
    ) String sortBy,
    @RequestParam(
      value = "sortDir",
      defaultValue = AppConstansts.SORT_DIR,
      required = false
    ) String sortDir
  ) {
    PostResponse postResponse =
      this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
    return new ResponseEntity<>(postResponse, HttpStatus.OK);
  }

  @GetMapping("/posts/{postId}")
  public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId) {
    PostDto posts = this.postService.getPostById(postId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @PutMapping("/posts/{postId}")
  public ResponseEntity<PostDto> Update(
    @RequestBody PostDto postDto,
    @PathVariable Integer postId
  ) {
    PostDto posts = this.postService.updatepost(postDto, postId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @DeleteMapping("/posts/{postId}")
  public ApiResponse deletePost(@PathVariable Integer postId) {
    this.postService.deletePost(postId);
    return new ApiResponse("Post deleted", true);
  }

  @GetMapping("/posts/search/{keywords}")
  public ResponseEntity<List<PostDto>> searchPost(
    @PathVariable String keywords
  ) {
    List<PostDto> posts = this.postService.searchPost(keywords);

    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @PostMapping("/post/image/upload/{postId}")
  public ResponseEntity<PostDto> uploadImage(
    @RequestParam("image") MultipartFile image,
    @PathVariable Integer postId
  )
    throws IOException {
      PostDto post = this.postService.getPostById(postId);
    String fileName = this.fileService.uploadImage(path, image);


    post.setImageName(fileName);
    PostDto p = this.postService.updatepost(post, postId);
    return new ResponseEntity<PostDto>(p, HttpStatus.OK);
  }

  @GetMapping(
    value = "post/image/{imageName}",
    produces = MediaType.IMAGE_JPEG_VALUE
  )
  public void downloadImage(
    @PathVariable String imageName,
    HttpServletResponse response
  )
    throws IOException {
    InputStream res = this.fileService.getResource(path, imageName);
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(res, response.getOutputStream());
  }
}
