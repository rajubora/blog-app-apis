package com.codewithraj.blog.controllers;

import com.codewithraj.blog.payloads.ApiResponse;
import com.codewithraj.blog.payloads.UserDto;
import com.codewithraj.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpTimeoutException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    // Post - create user
    @PostMapping("/")
     public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {            UserDto createUserDto =    this.userService.createUser(userDto);

                return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }


    // put - update user
 @PutMapping("/{userId}")
public ResponseEntity<UserDto> updateUser(  @Valid @RequestBody UserDto userDto , @PathVariable("userId") Integer uid){
    UserDto updateUser=    this.userService.updateUser(userDto,uid);
        return  ResponseEntity.ok(updateUser);

}

    // delete - delete the user
    @DeleteMapping("/{userId}")
public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId")  Integer uId)
{
    this.userService.deleteUser(uId);
    return new ResponseEntity(new ApiResponse("User Deleted successfully",true), HttpStatus.OK);

}

  // Get - user get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()

    {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }


    // get single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)

    {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }


}
