package com.codewithraj.blog.repositories;


import com.codewithraj.blog.entities.Category;
import com.codewithraj.blog.entities.Post;
import com.codewithraj.blog.entities.User;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List <Post> findByCategory(Category category);


    Page<Post> findByUserId(Integer userId, Pageable pageable);
}
