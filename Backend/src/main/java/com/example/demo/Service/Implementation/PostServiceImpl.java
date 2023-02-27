package com.example.demo.Service.Implementation;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Model.Category;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Payloads.CategoryDto;
import com.example.demo.Payloads.PostDto;
import com.example.demo.Payloads.PostResponse;
import com.example.demo.Repository.CategoryRepo;
import com.example.demo.Repository.PostRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.PostService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
  @Autowired
  private PostRepo postrepo;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private ModelMapper modelmap;

  @Autowired
  private CategoryRepo categoryRepo;

  @Override
  public PostDto createpost(
    PostDto postDto,
    Integer userId,
    Integer cateogryId
  ) {
    Post post = this.modelmap.map(postDto, Post.class);
    User user =
      this.userRepo.findById(userId)
        .orElseThrow(
          () -> new ResourceNotFoundException("User", "user id", userId)
        );
    Category cat =
      this.categoryRepo.findById(cateogryId)
        .orElseThrow(
          () ->
            new ResourceNotFoundException("Category", "Category id", cateogryId)
        );
    post.setAddedDate(new Date());
    post.setImageName("default");
    post.setUser(user);
    post.setCategory(cat);
    Post np = this.postrepo.save(post);
    return this.modelmap.map(np, PostDto.class);
  }

  @Override
  public PostDto updatepost(PostDto postDto, Integer postId) {
    // TODO Auto-generated method stub
    Post post =
      this.postrepo.findById(postId)
        .orElseThrow(
          () -> new ResourceNotFoundException("Post", "Post Id", postId)
        );
    post.setTitle(postDto.getTitle());
    post.setContent(postDto.getContent());
    post.setImageName(postDto.getImageName());
    Post updatedpost = this.postrepo.save(post);
    return this.modelmap.map(updatedpost, PostDto.class);
  }

  @Override
  public void deletePost(Integer postId) {
    // TODO Auto-generated method stub

    Post post =
      this.postrepo.findById(postId)
        .orElseThrow(
          () -> new ResourceNotFoundException("Post", "Post Id", postId)
        );
    this.postrepo.delete(post);
  }

  @Override
  public PostResponse getAllPost(
    Integer pageNumber,
    Integer pageSize,
    String sortBy,
    String sortDir
  ) {
    Sort sort = sortDir.equals("asc")
      ? Sort.by(sortBy).ascending()
      : Sort.by(sortBy).descending();

    Pageable p = PageRequest.of(pageNumber, pageSize, sort);
    Page<Post> pagePost = this.postrepo.findAll(p);
    List<Post> all = pagePost.getContent();
    List<PostDto> aa = all
      .stream()
      .map(x -> this.modelmap.map(x, PostDto.class))
      .collect(Collectors.toList());
    PostResponse postResponse = new PostResponse();
    postResponse.setContent(aa);
    postResponse.setPageNumber(pagePost.getNumber());
    postResponse.setPageSize(pagePost.getSize());
    postResponse.setTotalElements(pagePost.getTotalElements());
    postResponse.setTotalPages(postResponse.getTotalPages());
    postResponse.setLastPage(pagePost.isLast());
    return postResponse;
  }

  @Override
  public PostDto getPostById(Integer postId) {
    // TODO Auto-generated method stub
    Post p =
      this.postrepo.findById(postId)
        .orElseThrow(
          () -> new ResourceNotFoundException("Post", "post id", postId)
        );
    return this.modelmap.map(p, PostDto.class);
  }

  @Override
  public List<PostDto> getPostsByCategory(Integer categoryId) {
    Category cat =
      this.categoryRepo.findById(categoryId)
        .orElseThrow(
          () ->
            new ResourceNotFoundException("Category", "category Id", categoryId)
        );
    List<Post> posts = this.postrepo.findByCategory(cat);

    List<PostDto> coll = posts
      .stream()
      .map(post -> this.modelmap.map(post, PostDto.class))
      .collect(Collectors.toList());

    return coll;
  }

  @Override
  public List<PostDto> getPostsByUser(Integer userId) {
    // TODO Auto-generated method stub
    User user1 =
      this.userRepo.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
    List<Post> posts = this.postrepo.findByUser(user1);
    return posts
      .stream()
      .map(post -> this.modelmap.map(post, PostDto.class))
      .collect(Collectors.toList());
  }

  @Override
  public List<PostDto> searchPost(String keywords) {
    // TODO Auto-generated method stub
    List<Post> posts = this.postrepo.searchByTitle("%"+keywords+"%");
    List<PostDto> pp = posts
      .stream()
      .map(p -> this.modelmap.map(p, PostDto.class))
      .collect(Collectors.toList());

    return pp;
  }
}
