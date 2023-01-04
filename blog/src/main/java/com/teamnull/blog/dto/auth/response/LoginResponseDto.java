package com.teamnull.blog.dto.auth.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoginResponseDto {
    private final HttpStatus status;
    private final String message;

    public LoginResponseDto(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
