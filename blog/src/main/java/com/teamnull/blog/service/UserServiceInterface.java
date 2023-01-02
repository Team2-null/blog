package com.teamnull.blog.service;

import javax.servlet.http.HttpServletRequest;

import com.teamnull.blog.dto.auth.request.LoginRequestDto;
import com.teamnull.blog.dto.auth.request.SignupRequestDto;

public interface UserServiceInterface {
    public void signup(SignupRequestDto signupRequestDto, HttpServletRequest request);

    public boolean login(LoginRequestDto loginRequestDto, HttpServletRequest request);
}
