package com.teamnull.blog.service;

import com.teamnull.blog.dto.auth.request.LoginRequestDto;
import com.teamnull.blog.dto.auth.request.SignupRequestDto;

import javax.servlet.http.HttpServletResponse;

public interface UserServiceInterface {
    public void signup(SignupRequestDto signupRequestDto);

    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response);
}
