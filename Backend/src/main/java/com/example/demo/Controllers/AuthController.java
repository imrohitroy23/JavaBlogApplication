package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Helper.JwtRequest;
import com.example.demo.Helper.JwtResponse;
import com.example.demo.Helper.JwtTokenHelper;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest request)throws Exception{
         this.authenticate(request.getUsername(),request.getPassword());
         UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
         String token=this.jwtTokenHelper.generateToken(userDetails);
         JwtResponse response=new JwtResponse();
         response.setToken(token);
         return new ResponseEntity<>(response,HttpStatus.OK);
    
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(username,password);
    try{
        this.authenticationManager.authenticate(authenticationToken);
    }catch(BadCredentialsException e){
        System.out.println("invalid details");
        throw new Exception("invalid username or password");
    }
        
    }
}
