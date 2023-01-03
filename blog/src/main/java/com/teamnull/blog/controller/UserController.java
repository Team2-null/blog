package com.teamnull.blog.controller;

import com.teamnull.blog.dto.auth.request.LoginRequestDto;
import com.teamnull.blog.dto.auth.request.SignupRequestDto;
import com.teamnull.blog.dto.auth.response.LoginResponseDto;
import com.teamnull.blog.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원이름, 패스워드를 입력받아 회원가입을 요청합니다.")
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return "회원가입에 성공했습니다.";
    }

    @ApiOperation(value = "로그인", notes = "회원이름, 패스워드를 입력받아 로그인을 요청합니다.")
    @PostMapping("/login")
    @ResponseBody
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return new LoginResponseDto(HttpStatus.OK, "로그인에 성공했습니다.");
    }
}
