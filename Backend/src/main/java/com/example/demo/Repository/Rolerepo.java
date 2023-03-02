package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Role;

public interface Rolerepo extends JpaRepository<Role,Integer>{
    
}
