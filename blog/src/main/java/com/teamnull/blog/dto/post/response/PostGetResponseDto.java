package com.teamnull.blog.dto.post.response;

import java.time.LocalDateTime;

import com.teamnull.blog.entity.Post;

import lombok.Getter;

@Getter
public class PostGetResponseDto {
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public PostGetResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreateAt();
        this.modifiedAt = post.getModifiedAt();
    }
}