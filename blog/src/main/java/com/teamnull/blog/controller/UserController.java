package com.teamnull.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamnull.blog.dto.auth.request.LoginRequestDto;
import com.teamnull.blog.dto.auth.request.SignupRequestDto;
import com.teamnull.blog.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public boolean signup(SignupRequestDto requestDto, HttpServletRequest request) {
        return userService.signup(requestDto, request);
    }

    @PostMapping("")
    public boolean login(LoginRequestDto requestDto, HttpServletRequest request) {
        return userService.login(requestDto, request);
    }
}
