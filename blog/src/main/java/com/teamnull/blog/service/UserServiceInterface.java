package com.teamnull.blog.service;

import com.teamnull.blog.dto.auth.request.LoginRequestDto;
import com.teamnull.blog.dto.auth.request.SignupRequestDto;

import javax.servlet.http.HttpServletRequest;

public interface UserServiceInterface {
    public boolean signup(SignupRequestDto signupRequestDto, HttpServletRequest request);

    public boolean login(LoginRequestDto loginRequestDto, HttpServletRequest request);
}