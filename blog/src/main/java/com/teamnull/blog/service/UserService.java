package com.teamnull.blog.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.teamnull.blog.dto.auth.request.LoginRequestDto;
import com.teamnull.blog.dto.auth.request.SignupRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface{

    @Override
    public boolean signup(SignupRequestDto signupRequestDto, HttpServletRequest request) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean login(LoginRequestDto loginRequestDto, HttpServletRequest request) {
        // TODO Auto-generated method stub
        return false;
    }

}
