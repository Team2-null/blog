package com.teamnull.blog.dto.post.response;

import lombok.Getter;

@Getter
public class PostDeleteResponseDto {
    private final String msg;
    private final int statuscode;

    public PostDeleteResponseDto(String msg, int statuscode) {
        this.msg = msg;
        this.statuscode = statuscode;
    }
}
