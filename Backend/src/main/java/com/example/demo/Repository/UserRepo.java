package com.example.demo.Repository;

import com.example.demo.Model.User;
import com.example.demo.Payloads.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {}
