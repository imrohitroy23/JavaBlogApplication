package com.example.demo.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Model.Comment;
import com.example.demo.Model.Post;
import com.example.demo.Payloads.CommentDto;
import com.example.demo.Repository.CommentRepo;
import com.example.demo.Repository.PostRepo;

@Service
public class CommentService {

    @Autowired
    private PostRepo postRepo;

@Autowired
private CommentRepo commentRepo;

@Autowired
public ModelMapper modelMapper;

    public CommentDto createComment(CommentDto commentDto, Integer postId){
        Post p =this.postRepo.findById(postId).orElseThrow(
            () -> new ResourceNotFoundException("Post", "post id", postId)
          );
        Comment comm=this.modelMapper.map(commentDto,Comment.class);
        comm.setPost(p);
        Comment saved=this.commentRepo.save(comm);
//yaha check kr lena



















            return this.modelMapper.map(saved, CommentDto.class);
    }    

 public void deleteComment(Integer commentId){
Comment com=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment ID", commentId));

this.commentRepo.delete(com);
}
}
