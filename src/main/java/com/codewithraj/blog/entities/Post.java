package com.codewithraj.blog.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor

public class Post {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer postId;

      @Column(name = "postTitle", length=100, nullable = false )
      private String title;

       @Column(name ="postContent", length = 1000, nullable = false)
       private  String  content;

       @Column(name = "image")
       private String imageName;

       @Column(name = "postAddDate")
        private Date addedDate;


       @ManyToOne
       @JoinColumn(name = "category_id")
       private Category category;

      @ManyToOne
      @JoinColumn(name = "user_id")
       private  User user ;

}
