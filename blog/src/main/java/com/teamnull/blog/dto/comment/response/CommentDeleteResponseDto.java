package com.teamnull.blog.dto.comment.response;

import lombok.Getter;

@Getter
public class CommentDeleteResponseDto {
    private final String msg;
    private final int statuscode;

    public CommentDeleteResponseDto(String msg, int statuscode) {
        this.msg = msg;
        this.statuscode = statuscode;
    }
}
