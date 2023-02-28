package com.example.demo.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotEmpty
  @Column(name="user_name")
  @Size(min = 4, message = "name must be min of 4 chars")
  private String name;

  @Email(message = "email id is not valid")
  private String email;

  @NotEmpty
  private String about;

  @NotEmpty
  @Size(min = 3, max = 100, message = "password must be of 3-10 chars")
  private String password;

  @OneToMany(
    mappedBy = "user",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
  )
  private List<Post> posts = new ArrayList<>();

  @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  @JoinTable(name="user_role")
  @JoinColumn(name="user",referencedColumnName = "id")
  @JoinColumn(name="role",referencedColumnName = "id")
  private Set<Role> roles=new HashSet<>();

  @Override
  public boolean isEnabled(){
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
  List<SimpleGrantedAuthority> rr=  this.roles.stream().map((r)->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
   return rr;
  }

  @Override
  public String getUsername() {
  
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

}
