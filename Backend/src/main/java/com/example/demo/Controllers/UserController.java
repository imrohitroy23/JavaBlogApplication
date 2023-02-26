package com.example.demo.Controllers;

import com.example.demo.Model.User;
import com.example.demo.Payloads.ApiResponse;
import com.example.demo.Payloads.UserDto;
import com.example.demo.Service.UserService;
import java.util.List;
import java.util.Map;
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

@RequestMapping("/user")
@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/")
  public ResponseEntity<UserDto> createdUser(@Valid @RequestBody UserDto user) {
    UserDto create = this.userService.addUser(user);
    return new ResponseEntity<UserDto>(create, HttpStatus.CREATED);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<UserDto> updateUser(
    @Valid @RequestBody UserDto user,
    @PathVariable Integer userId
  ) {
    UserDto upUser = this.userService.updateUser(user, userId);

    return ResponseEntity.ok(upUser);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<ApiResponse> deluser(
    @PathVariable("userId") Integer userId
  ) {
    this.userService.delUser(userId);
    return new ResponseEntity<>(
      new ApiResponse("User deleted", true),
      HttpStatus.OK
    );
  }

  @GetMapping("/")
  public ResponseEntity<List<UserDto>> getAll() {
    return ResponseEntity.ok(this.userService.getAll());
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getSingle(@PathVariable Integer userId) {
    return ResponseEntity.ok(this.userService.getUserById(userId));
  }
}
