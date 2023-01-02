package com.teamnull.blog.controller;

import javax.servlet.http.HttpServletRequest;

import com.teamnull.blog.dto.auth.request.LoginRequestDto;
import com.teamnull.blog.dto.auth.request.SignupRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamnull.blog.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public boolean signup(SignupRequestDto requestDto, HttpServletRequest request) {
        return userService.signup(requestDto, request);
    }

    @PostMapping("/login")
    public boolean login(LoginRequestDto requestDto, HttpServletRequest request) {
        return userService.login(requestDto, request);
    }
}
