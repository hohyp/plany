package com.toy.plany.controller;

import com.toy.plany.dto.dtos.TokenDto;
import com.toy.plany.dto.request.user.LoginRequest;
import com.toy.plany.dto.response.auth.LoginResponse;
import com.toy.plany.jwt.JwtFilter;
import com.toy.plany.jwt.TokenProvider;
import com.toy.plany.service.UserService;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "로그인하면 유저 정보와 jwt 토큰을 반환한다")
    public ResponseEntity<LoginResponse> authorize(@Valid @RequestBody LoginRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmployeeNumber(), request.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        TokenDto tokenDto = new TokenDto(jwt);
        LoginResponse loginResponse = userService.login(request);
        loginResponse = loginResponse.addToken(tokenDto);
        return new ResponseEntity<>(loginResponse, httpHeaders, HttpStatus.OK);
    }
}
