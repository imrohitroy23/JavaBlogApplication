package com.example.demo.Service;


import com.example.demo.Payloads.CategoryDto;
import java.util.List;

public interface CategoryService {
  public CategoryDto createCategory(CategoryDto category);

  public CategoryDto UpdateCategory(CategoryDto category, Integer categoryId);

  public void deleteCategory(Integer categoryId);

  public CategoryDto getCategory(Integer categoryId);

  public List<CategoryDto> getCategoryAll();
}
