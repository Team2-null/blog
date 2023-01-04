package com.teamnull.blog.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleApiRequestException(IllegalArgumentException ex) {
        RestApiException restApiException = new RestApiException(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
                                                                // RestApiException에 생성자를 만들어서 Setter대신 값을 넣어줌
        return new ResponseEntity<>(
                restApiException, // ResponseEntity에 들어가는 body값
                HttpStatus.BAD_REQUEST // 포스트맨에 보이는 400 Bad request가 얘임
        );
    }
}