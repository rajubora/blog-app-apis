package com.codewithraj.blog.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="users")
@NoArgsConstructor
@Getter
@Setter
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name="id")
   private Integer Id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String name;
    private String about;
    private String email;
    private String password;

    @OneToMany(mappedBy ="user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Post> posts =new ArrayList<>();


}
