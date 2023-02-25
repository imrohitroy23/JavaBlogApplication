package com.example.demo.Service.Implementation;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Model.Category;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Payloads.PostDto;
import com.example.demo.Repository.CategoryRepo;
import com.example.demo.Repository.PostRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.PostService;


@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepo postrepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelmap;

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createpost(PostDto postDto,Integer userId, Integer cateogryId) {

        Post post=this.modelmap.map(postDto,Post.class);
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user id", userId));
        Category cat=this.categoryRepo.findById(cateogryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", cateogryId));
        post.setAddedDate(new Date());
        post.setImageName("default");
        post.setUser(user);
        post.setCategory(cat);
        Post np=this.postrepo.save(post);
        return this.modelmap.map(np,PostDto.class);
    }

    @Override
    public Post updatepost(PostDto post, Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatepost'");
    }

    @Override
    public void deletePost(Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }

    @Override
    public List<Post> getAllPost() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPost'");
    }

    @Override
    public Post getPostById(Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostById'");
    }

    @Override
    public List<Post> getPostsByCategory(Integer categoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostsByCategory'");
    }

    @Override
    public List<Post> getPostsByUser(Integer userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostsByUser'");
    }

    @Override
    public List<Post> searchPost(String keywords) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPost'");
    }
    
}
