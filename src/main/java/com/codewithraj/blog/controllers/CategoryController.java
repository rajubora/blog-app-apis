package com.codewithraj.blog.controllers;

import com.codewithraj.blog.payloads.ApiResponse;
import com.codewithraj.blog.payloads.CategoryDto;
import com.codewithraj.blog.payloads.UserDto;
import com.codewithraj.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //Post mapping
    @PostMapping("/")
   public ResponseEntity<CategoryDto>  createCategory(@Valid @RequestBody CategoryDto categoryDto){
     CategoryDto createcategory =    this.categoryService.createCategory(categoryDto);

       return new ResponseEntity<CategoryDto>(createcategory, HttpStatus.CREATED);
   }


    //put mapping

    @PutMapping("/catId")
    public ResponseEntity<CategoryDto>  updateCategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable Integer catId ){
        CategoryDto updateCategory =    this.categoryService.updateCategory(categoryDto,catId);

        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }


    // get mapping
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>  getCategory(@PathVariable Integer categoryId)

    {
        return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
    }




    //get all mapping

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllUsers()

    {
        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }


    //delete controller
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer categoryId)
    {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity(new ApiResponse("User Deleted successfully",true), HttpStatus.OK);

    }



}
