package com.codewithraj.blog.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
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
       private Category category;

      @ManyToOne
       private  User user ;

}
