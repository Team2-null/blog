package com.teamnull.blog.service;

import javax.servlet.http.HttpServletRequest;

import com.teamnull.blog.dto.auth.request.LoginRequestDto;
import com.teamnull.blog.dto.auth.request.SignupRequestDto;

public interface UserServiceInterface {
    boolean signup(SignupRequestDto signupRequestDto, HttpServletRequest request);
    boolean login(LoginRequestDto loginRequestDto, HttpServletRequest request);
}
