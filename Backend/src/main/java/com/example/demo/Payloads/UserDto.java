package com.example.demo.Payloads;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDto {
  private int id;
  private String name;
  private String email;
  private String password;
  private String about;
  private Set<RoleDto> roles=new HashSet<>();
}
