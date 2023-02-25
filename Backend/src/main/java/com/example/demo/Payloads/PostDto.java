package com.example.demo.Payloads;

import java.util.Date;

import com.example.demo.Model.Category;
import com.example.demo.Model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer id;
    private String title;
    private String content;
   private String imageName;
   private Date addedDate;
   private CategoryDto category;
   private UserDto User;
}
