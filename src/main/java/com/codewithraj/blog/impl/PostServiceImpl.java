package com.codewithraj.blog.impl;
import com.codewithraj.blog.entities.Category;
import com.codewithraj.blog.entities.Post;
import com.codewithraj.blog.entities.User;
import com.codewithraj.blog.exceptions.ResourceNotFoundException;
import com.codewithraj.blog.payloads.PostDto;
import com.codewithraj.blog.payloads.PostResponse;
import com.codewithraj.blog.repositories.CategoryRepo;
import com.codewithraj.blog.repositories.PostRepo;
import com.codewithraj.blog.repositories.UserRepo;
import com.codewithraj.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer Id, Integer categoryId) {

        User user =this.userRepo.findById(Id).orElseThrow(()-> new ResourceNotFoundException("User", "Id", Id));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

                  Post post= this.dtoToPost(postDto);
                  post.setImageName("default.png");
                  post.setAddedDate(new Date());
                  post.setUser(user);
                  post.setCategory(category);
                Post post1 =this.postRepo.save(post);
                return this.postToDto(post1);

    }

    @Override
    public Post updatePost(PostDto postDto) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize) {

        Pageable pageable= PageRequest.of(pageNumber,pageSize);
         Page<Post> pagepost =  this.postRepo.findAll(pageable);

         List<Post> allposts=pagepost.getContent();


      List<PostDto> postDtos = allposts.stream().map((post) -> this.postToDto(post)).collect(Collectors.toList());

         PostResponse postResponse=new PostResponse();
         postResponse.setContent(postDtos);;
         postResponse.setPageNumber(pagepost.getNumber());
         postResponse.setLastPage(pagepost.isLast());
         postResponse.setPageSize(pagepost.getSize());
         postResponse.setTotalElements(pagepost.getNumberOfElements());
         postResponse.setTotalPages(pagepost.getTotalPages());


        return postResponse;
    }

    @Override
    public PostDto getSinglePost(Integer postId) {
        Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId ));
        return this.postToDto(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

       Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId",categoryId));
       List<Post> posts= this.postRepo.findByCategory(cat);
       List<PostDto> postDtos = posts.stream().map((post) -> this.postToDto(post)).collect(Collectors.toList());
       return  postDtos;
    }

    @Override
    public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize) {

        User users= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userID",userId));

        Pageable p=PageRequest.of(pageNumber,pageSize);
        Page<Post> pagepost=this.postRepo.findByUserId(userId,p);

        List<Post> posts=pagepost.getContent();


        List<PostDto> postDtos1=   posts.stream().map((user)->this.postToDto(user)).collect(Collectors.toList());

     PostResponse postResponse=new PostResponse();

     postResponse.setContent(postDtos1);
     postResponse.setTotalElements(pagepost.getNumberOfElements());
     postResponse.setPageSize(pagepost.getSize());
     postResponse.setLastPage(pagepost.isLast());
     postResponse.setTotalPages(postResponse.getTotalPages());
     postResponse.setPageNumber(pagepost.getNumber());



     return postResponse;


    }

    @Override
    public List<Post> searchPosts(String keyword) {


        return null;
    }
    private Post dtoToPost(PostDto postDto)
    {

     Post post= this.modelMapper.map(postDto,Post.class);
        return post;
    }
    public  PostDto postToDto(Post post)
    {

        PostDto postDto= this.modelMapper.map(post,PostDto.class);
        return postDto;
    }


}
