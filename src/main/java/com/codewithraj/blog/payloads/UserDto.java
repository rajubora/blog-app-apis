package com.codewithraj.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min=4, message = "username must be of 4 characters !")
    private String name;


    @NotEmpty
    private String about;

    @Email(message = "your given email is not valid !")
    private String email;

    @NotEmpty
    @Size(min=3,max=10, message = "password should be of min 3 characters")
    private String password;
}
