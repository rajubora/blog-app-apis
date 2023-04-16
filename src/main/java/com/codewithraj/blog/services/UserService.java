package com.codewithraj.blog.services;


import com.codewithraj.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

UserDto createUser (UserDto user);
UserDto updateUser(UserDto userDto, Integer userId);
UserDto getUserById(Integer userId);
List <UserDto> getAllUsers();
void deleteUser(Integer userId);


}
