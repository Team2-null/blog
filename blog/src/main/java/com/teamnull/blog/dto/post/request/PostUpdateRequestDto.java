package com.teamnull.blog.dto.post.request;

import lombok.Getter;

@Getter
public class PostUpdateRequestDto {
    private String title;
    private String writer;
    private String content;
    private String password;
}
