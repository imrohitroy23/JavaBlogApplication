package com.example.demo.Service.Implementation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Model.User;
import com.example.demo.Payloads.UserDto;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.UserService;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User saved=this.userRepo.save(user);
        return this.UsertoDto(saved);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
    
        User user1=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId)) ;
        user1.setAbout(userDto.getAbout());
        user1.setEmail(userDto.getEmail());
        user1.setName(userDto.getName());
        user1.setPassword(userDto.getPassword());
        User updatedUSer=this.userRepo.save(user1);
        return this.UsertoDto(updatedUSer); 
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id" , userId));
        return this.UsertoDto(user);
    }

    @Override
    public List<UserDto> getAll() {
       
        List<User> users=this.userRepo.findAll();
        List<UserDto> userDtos=users.stream().map(user-> this.UsertoDto(user)).collect(Collectors.toList());
        return  userDtos;
    }

    @Override
    public void delUser(Integer userId) {
       
        User user1=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId)) ;
        
        this.userRepo.delete(user1);
    }
    public User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
        return user;
    }
    public UserDto UsertoDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        // userDto.setId(user.getId());
        // userDto.setAbout(user.getAbout());
        // userDto.setEmail(user.getEmail());
        // userDto.setName(user.getName());
        // userDto.setPassword(user.getPassword());
        return userDto;
    }
}
