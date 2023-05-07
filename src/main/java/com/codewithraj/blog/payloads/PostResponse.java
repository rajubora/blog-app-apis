package com.codewithraj.blog.payloads;

import lombok.Data;

import java.util.List;

@Data

public class PostResponse {
    private List<PostDto> content;
    private  int pageNumber;
    private  int pageSize;
    private int totalElements;
    private  int totalPages;
    private  boolean lastPage;





}


