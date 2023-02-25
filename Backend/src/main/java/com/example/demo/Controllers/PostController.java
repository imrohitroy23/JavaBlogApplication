package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Post;
import com.example.demo.Payloads.PostDto;
import com.example.demo.Service.PostService;

@RestController
@RequestMapping("/")
public class PostController {
    @Autowired
    private PostService postService;
     

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createpost(@RequestBody PostDto post,    @PathVariable Integer userId, @PathVariable Integer categoryId){

        PostDto cr=this.postService.createpost(post, userId, categoryId);
        return new ResponseEntity<PostDto>(cr,HttpStatus.CREATED);
    }
}
