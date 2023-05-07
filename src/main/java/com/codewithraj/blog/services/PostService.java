package com.codewithraj.blog.services;

import com.codewithraj.blog.entities.Post;
import com.codewithraj.blog.payloads.PostDto;
import com.codewithraj.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto , Integer Id, Integer categoryId);

    // update

    Post updatePost(PostDto postDto);

    //delete
    void deletePost (Integer postId);

    // get all posts
   PostResponse  getAllPosts(Integer pageNumber, Integer pageSize);

    //get single post

    PostDto getSinglePost(Integer postId);


    //get all post by category

    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all post by user
   PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize);

    //Search posts
    List<Post>  searchPosts(String keyword);









}
