package com.teamnull.blog.dto.comment.request;


import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String contents;
    private Integer likes = 0;

}
