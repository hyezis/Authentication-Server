package com.example.authenticationserver.controller;

import com.example.authenticationserver.dto.IDPWDto;
import com.example.authenticationserver.dto.JwtToken;
import com.example.authenticationserver.global.BaseException;
import com.example.authenticationserver.global.BaseResponse;
import com.example.authenticationserver.global.BaseResponseStatus;
import com.example.authenticationserver.service.LoginService;
import com.example.authenticationserver.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.example.authenticationserver.global.BaseResponseStatus.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public BaseResponse<JwtToken> login(
            @RequestBody IDPWDto idpwDto, HttpServletResponse response
    ) throws BaseException {
        JwtToken token = loginService.login(idpwDto,response);
        return new BaseResponse<>(token);
    }

    @PostMapping("/logout")
    public BaseResponse<Void> logout(HttpServletRequest request, HttpServletResponse response) throws BaseException {
        userService.logout(request,response);
        return new BaseResponse<>(SUCCESS);
    }
}
