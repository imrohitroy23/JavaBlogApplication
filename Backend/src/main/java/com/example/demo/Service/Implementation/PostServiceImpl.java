package com.example.demo.Service.Implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Model.Category;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Payloads.CategoryDto;
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
    public PostDto updatepost(PostDto post, Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatepost'");
    }

    @Override
    public void deletePost(Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }

    @Override
    public List<PostDto> getAllPost() {
        // TODO Auto-generated method stub
        List<Post> all=this.postrepo.findAll();
        List<PostDto> aa=all.stream().map((x)->this.modelmap.map(x,PostDto.class)).collect(Collectors.toList());
        return aa;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        // TODO Auto-generated method stub
       Post p= this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
       return this.modelmap.map(p,PostDto.class);
       
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));
        List<Post> posts=this.postrepo.findByCategory(cat);

        List<PostDto> coll=posts.stream().map((post)->this.modelmap.map(post,PostDto.class)).collect(Collectors.toList());
     
        return coll;
    }



    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        // TODO Auto-generated method stub
        User user1=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId)) ;
        List<Post> posts=this.postrepo.findByUser(user1);
        return posts.stream().map((post)->this.modelmap.map(post,PostDto.class)).collect(Collectors.toList());
       
    }



    @Override
    public List<PostDto> searchPost(String keywords) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPost'");
    }
    
}
