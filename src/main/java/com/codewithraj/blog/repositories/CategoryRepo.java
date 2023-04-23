package com.codewithraj.blog.repositories;

import com.codewithraj.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {



}
