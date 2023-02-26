package com.example.demo.Controllers;

import com.example.demo.Model.Category;
import com.example.demo.Payloads.CategoryDto;
import com.example.demo.Service.CategoryService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/category")
@RestController
public class CategoryController {
  @Autowired
  private CategoryService userService;

  @PostMapping("/")
  public ResponseEntity<CategoryDto> createdUser(
    @Valid @RequestBody CategoryDto category
  ) {
    CategoryDto create = this.userService.createCategory(category);
    return new ResponseEntity<>(create, HttpStatus.CREATED);
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> updateUser(
    @Valid @RequestBody CategoryDto category,
    @PathVariable Integer categoryId
  ) {
    CategoryDto upUser = this.userService.UpdateCategory(category, categoryId);

    return ResponseEntity.ok(upUser);
  }

  @DeleteMapping("/{categoryId}")
  public void deluser(@PathVariable("categoryId") Integer categoryId) {
    this.userService.deleteCategory(categoryId);
  }

  @GetMapping("/")
  public ResponseEntity<List<CategoryDto>> getAll() {
    return ResponseEntity.ok(this.userService.getCategoryAll());
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> getSingle(
    @PathVariable Integer categoryId
  ) {
    return ResponseEntity.ok(this.userService.getCategory(categoryId));
  }
}
