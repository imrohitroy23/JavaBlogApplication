package com.example.demo.Model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer categoryId;

  @Column(name = "title", length = 100, nullable = false)
  @NotBlank
  @Size(min = 4)
  private String categoryTitle;

  @Column(name = "description")
  @NotBlank
  @Size(min = 4)
  private String categoryDescription;

  @OneToMany(
    mappedBy = "category",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
  )
  private List<Post> posts = new ArrayList<>();
}
