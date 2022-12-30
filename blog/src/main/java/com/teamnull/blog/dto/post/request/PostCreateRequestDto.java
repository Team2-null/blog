package com.teamnull.blog.dto.request;

import lombok.Getter;

@Getter
public class PostCreateRequestDto {
    private String title;
    private String writer;
    private String content;
    private String password;
}
