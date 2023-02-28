package com.example.demo.Payloads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepo;


@Service
public class CustomeUserDeatils implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub

        User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("username with ", "Email Id : "+username, 0));

        return user;
    }
    
}
