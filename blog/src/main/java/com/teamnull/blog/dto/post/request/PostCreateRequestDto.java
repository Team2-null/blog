package com.teamnull.blog.dto.post.request;

import lombok.Getter;

@Getter
public class PostCreateRequestDto {
    
    private String title;
    private String content;

    public PostCreateRequestDto(String title, String contents) {
        this.title = title;
        this.content = contents;
    }
}
