package com.teamnull.blog.dto.comment.response;

import com.teamnull.blog.entity.Comment;

public class CommentResponseDto {
    private String username;
    private String contents;


    public CommentResponseDto(Comment comment) {
        this.username = comment.getUser().getUsername();
        this.contents = comment.getContents();
    }
}
