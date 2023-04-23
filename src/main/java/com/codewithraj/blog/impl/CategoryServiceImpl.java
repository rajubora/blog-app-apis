package com.codewithraj.blog.impl;

import com.codewithraj.blog.entities.Category;
import com.codewithraj.blog.entities.User;
import com.codewithraj.blog.exceptions.ResourceNotFoundException;
import com.codewithraj.blog.payloads.CategoryDto;
import com.codewithraj.blog.payloads.UserDto;
import com.codewithraj.blog.repositories.CategoryRepo;
import com.codewithraj.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

      Category category =this.dtoToCategory(categoryDto);
      Category savedUser= this.categoryRepo.save(category);
        return this.categoryToDto(savedUser);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
       Category cat=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId ));

       cat.setCategoryDescription(categoryDto.getCategoryDescription());
       cat.setCategoryTitle(categoryDto.getCategoryTitle());
       Category categoryupdate =    this.categoryRepo.save(cat);
        CategoryDto categoryDto1=this.categoryToDto(categoryupdate);

       return categoryDto1 ;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId ));
        this.categoryRepo.delete(cat);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId ));

        return this.categoryToDto(cat);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
                List<Category> categories=this.categoryRepo.findAll();
        List <CategoryDto> categoryDtos = categories.stream().map(cat -> this.categoryToDto(cat)).collect(Collectors.toList());

        return categoryDtos;
    }

    private Category dtoToCategory(CategoryDto categoryDto){

        Category cat= this.modelMapper.map(categoryDto,Category.class);
         return cat;
    }
      public CategoryDto categoryToDto(Category category ){
     CategoryDto categoryDto=   this.modelMapper.map(category,CategoryDto.class);
        return categoryDto;

     }

}
