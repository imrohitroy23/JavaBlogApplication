package com.example.demo.Service;


import com.example.demo.Payloads.UserDto;
import java.util.List;

public interface UserService {
  public UserDto addUser(UserDto user);

  public UserDto updateUser(UserDto user, Integer userId);

  public UserDto getUserById(Integer userId);

  public List<UserDto> getAll();

  public void delUser(Integer userId);
}
