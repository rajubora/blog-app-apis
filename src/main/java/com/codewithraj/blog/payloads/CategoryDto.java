package com.codewithraj.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private String categoryId;
    @NotBlank
    @Size(min=4)
    private String categoryTitle;
    @NotBlank
    @Size(min=10)
    private String categoryDescription;



}
