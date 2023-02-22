package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<User> createdUser(@RequestBody User user){
        User create=this.userService.addUser(user);
        return new ResponseEntity<>(create,HttpStatus.CREATED);
    }
    
}
