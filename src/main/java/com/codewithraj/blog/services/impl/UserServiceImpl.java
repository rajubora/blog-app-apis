package com.codewithraj.blog.services.impl;

import com.codewithraj.blog.entities.User;
import com.codewithraj.blog.exceptions.ResourceNotFoundException;
import com.codewithraj.blog.payloads.UserDto;
import com.codewithraj.blog.repositories.UserRepo;
import com.codewithraj.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {

User user =this.dtoToUser(userDto);
  User savedUser= this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId ));

        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
       User updateUser=  this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updateUser);
        return userDto1;

    }

    @Override
    public UserDto getUserById(Integer userId) {

      User user=  this.userRepo.findById(userId).orElseThrow(() ->new ResourceNotFoundException("User", "Id", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

      List<User> users=  this.userRepo.findAll();
    List <UserDto> userDtos  = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

      return userDtos;


    }

    @Override
    public void deleteUser(Integer userId) {

         User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id",userId));
           this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto){

    User user=this.modelMapper.map(userDto,User.class);
        //  User user=new User() ;
        //  user.setId(userDto.getId());
        //   user.setAbout(userDto.getAbout());
        //   user.setEmail(userDto.getEmail());
        //  user.setName(userDto.getName());
        // user.setPassword(userDto.getPassword());

        return  user;
    }
    public UserDto userToDto(User user)
    {

         UserDto userDto =this.modelMapper.map(user, UserDto.class );
        //  UserDto userDto =new UserDto();
        // userDto.setAbout(user.getAbout());
        // userDto.setEmail(user.getEmail());
        // userDto.setId(user.getId());
        // userDto.setName(user.getName());
        // userDto.setPassword(user.getPassword());
            return userDto;
    }




}
