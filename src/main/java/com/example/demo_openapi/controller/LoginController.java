package com.example.demo_openapi.controller;

import com.example.demo_openapi.model.KakaoToken;
import com.example.demo_openapi.service.LoginService;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    private final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @ResponseBody
    @GetMapping("/api/auth/kakao")
    public String login(@RequestParam("code") String code, HttpServletResponse response) {
        String token = loginService.getKakaoToken(code);

        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge((int) TimeUnit.MINUTES.toSeconds(JWT_TOKEN_VALIDITY));
        cookie.setPath("/");
        response.addCookie(cookie);

        return "/auth/success";
    }

    @ResponseBody
    @PostMapping("/api/auth/google")
    public String googleLogin(@RequestParam("credential") String credential, HttpServletResponse response) {
        String token = loginService.getGoogleToken(credential);

        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge((int) TimeUnit.MINUTES.toSeconds(JWT_TOKEN_VALIDITY));
        cookie.setPath("/");
        response.addCookie(cookie);

        return "/auth/success";
    }
}
