package com.codewithraj.blog.services;

import com.codewithraj.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    // Create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto,   Integer categoryId);

    // delete
    void deleteCategory(Integer categoryId);

    // get
    CategoryDto getCategory(Integer categoryId);

    // getall
    List <CategoryDto> getAllCategory();

}
