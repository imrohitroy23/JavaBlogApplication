package com.example.demo.Service.Implementation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Model.Category;
import com.example.demo.Payloads.CategoryDto;
import com.example.demo.Repository.CategoryRepo;
import com.example.demo.Service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

@Autowired
private ModelMapper modelmap;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        
        Category cat=this.modelmap.map(categoryDto, Category.class);
        Category add=this.categoryRepo.save(cat);
        return this.modelmap.map(add,CategoryDto.class);
      
    }

    @Override
    public CategoryDto UpdateCategory(CategoryDto categoryDto, Integer categoryId) {
       
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());

        Category c= this.categoryRepo.save(cat);
        return this.modelmap.map(c,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        this.categoryRepo.delete(cat);
     
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));

        return this.modelmap.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategoryAll() {
        
        List<Category> cat2=this.categoryRepo.findAll();
        List<CategoryDto> cat1= cat2.stream().map((cat)->this.modelmap.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return cat1;
        
    }
    
}
