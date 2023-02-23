package com.example.demo.Controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
 
    @PostMapping("/")
    public ResponseEntity<User> createdUser(@Valid @RequestBody User user){
        User create=this.userService.addUser(user);
        return new ResponseEntity<>(create,HttpStatus.CREATED);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable Integer userId){
           User upUser= this.userService.updateUser(user, userId);
   
           return ResponseEntity.ok(upUser);
    }
    
    @DeleteMapping("/{userId}")
    public void deluser(@PathVariable("userId") Integer userId){
        this.userService.delUser(userId);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(this.userService.getAll());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingle(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
    
}
