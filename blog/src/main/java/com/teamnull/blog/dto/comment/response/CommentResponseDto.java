package com.teamnull.blog.dto.comment.response;

import com.teamnull.blog.entity.Comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String username;
    private String contents;
    private Integer likeCount;

    public CommentResponseDto(Comment comment) {
        this.username = comment.getUser().getUsername();
        this.contents = comment.getContents();
        this.likeCount = comment.getLikes();
    }
}
