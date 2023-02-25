package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.Category;
import com.example.demo.Payloads.CategoryDto;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto category);

    public CategoryDto UpdateCategory(CategoryDto category,Integer categoryId);

    public void deleteCategory(Integer categoryId);

    public CategoryDto getCategory(Integer categoryId);

    public List<CategoryDto> getCategoryAll();    
}
