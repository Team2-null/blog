package com.teamnull.blog.dto.comment.request;

import com.teamnull.blog.entity.Comment;
import com.teamnull.blog.entity.Post;
import com.teamnull.blog.entity.User;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long commentId;
    private String contents;
    private Integer likes = 0;

    // public Comment toEntity(){
    //     Comment comments = Comment.builder()
    //             .id(commentId)
    //             .contents(contents)
    //             .build();

    //     return comments;
    // }
}
