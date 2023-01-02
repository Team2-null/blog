package com.teamnull.blog.dto.comment.request;

import com.teamnull.blog.entity.Comment;
import com.teamnull.blog.entity.Post;
import com.teamnull.blog.entity.User;

public class CommentRequestDto {
    private Long commentId;
    private String contents;


    public Comment toEntity(){
        Comment comments = Comment.builder()
                .id(commentId)
                .contents(contents)
                .build();

        return comments;
    }
}
