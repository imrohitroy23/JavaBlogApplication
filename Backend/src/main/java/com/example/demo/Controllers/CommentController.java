package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Payloads.ApiResponse;
import com.example.demo.Payloads.CommentDto;
import com.example.demo.Service.CommentService;

@RequestMapping("/comment")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("post/{postId}/comment/")
    public ResponseEntity<CommentDto> create(@RequestBody CommentDto comment,@PathVariable Integer postId){
            CommentDto com=this.commentService.createComment(comment,postId);
            return new ResponseEntity<CommentDto>(com, HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> DeleteComm(@PathVariable Integer commentId){
this.commentService.deleteComment(commentId);
            return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted",true), HttpStatus.OK);
    }
}
