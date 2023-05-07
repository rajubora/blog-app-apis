package com.codewithraj.blog.controllers;

import com.codewithraj.blog.payloads.PostDto;
import com.codewithraj.blog.payloads.PostResponse;
import com.codewithraj.blog.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto  , @PathVariable Integer userId , @PathVariable Integer  categoryId)
    {
        PostDto createPosts=this.postService.createPost(postDto,userId,categoryId);

        return new ResponseEntity<PostDto>(createPosts, HttpStatus.CREATED);
    }

    // get posts by user

    @GetMapping("/user/{userId}/posts")
public ResponseEntity <PostResponse> getPostsByUser(@PathVariable Integer userId, @RequestParam (value = "pageNumber" , defaultValue = "0", required = false) Integer pageNumber, @RequestParam (value = "pageSize" , defaultValue = "2", required = false) Integer pageSize )
    {
       PostResponse posts= this.postService.getPostsByUser(userId, pageNumber,pageSize);
        return new ResponseEntity <PostResponse> (posts, HttpStatus.OK);

    }

    // get post by Category


    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity <List<PostDto>>  getPostsByCategory(@PathVariable Integer categoryId)
    {
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

    }

    @GetMapping("/posts")
    public ResponseEntity <PostResponse>   getAllPosts(@RequestParam  (value="pageNumber",defaultValue = "0", required = false)Integer pageNumber , @RequestParam (value = "pageSize", defaultValue = "5", required = false) Integer pageSize )
    {
     PostResponse allPosts =    this.postService.getAllPosts(pageNumber,pageSize);
     return new ResponseEntity <PostResponse> (allPosts,HttpStatus.OK);
    }



    @GetMapping("/posts/{postId}")
    public ResponseEntity <PostDto>  getSinglePost(@PathVariable Integer postId)
    {
              PostDto singlePost =    this.postService.getSinglePost(postId);
        return new ResponseEntity<PostDto>(singlePost,HttpStatus.OK);
    }





}
