package com.teamnull.blog.dto.like.response;

import lombok.Getter;

@Getter
public class CommentLikeResponseDto {
    private final String msg;
    private final int statuscode;

    public CommentLikeResponseDto(String msg, int statuscode) {
        this.msg = msg;
        this.statuscode = statuscode;
    }
}
