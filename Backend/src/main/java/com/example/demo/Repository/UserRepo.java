package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;
import com.example.demo.Payloads.UserDto;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    
}
