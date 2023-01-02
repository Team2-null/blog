package com.teamnull.blog.controller;

import com.teamnull.blog.dto.auth.request.LoginRequestDto;
import com.teamnull.blog.dto.auth.request.SignupRequestDto;
import com.teamnull.blog.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원이름, 패스워드를 입력받아 회원가입을 요청합니다.")
    @PostMapping("/signup")
    public String signup(SignupRequestDto signupRequestDto, HttpServletRequest request) {
        userService.signup(signupRequestDto, request);
        return "회원가입에 성공했습니다.";
    }

    @PostMapping("")
    public boolean login(LoginRequestDto requestDto, HttpServletRequest request) {
        return userService.login(requestDto, request);
    }
}
