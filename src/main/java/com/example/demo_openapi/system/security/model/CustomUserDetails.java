package com.example.demo_openapi.system.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {

    private User user;
    private String username; //사용자이름
    private String email;    //사용자 아이디
    private String password; //사용자이름
    private String phoneno;  //휴대폰번호
    private Collection<GrantedAuthority> authorities;	//권한 목록

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자가 가지고 있는 권한
        return authorities;
    }

    @Override
    public String getPassword() {
        // 사용자 패스워드
        return password;
    }

    public String getUsername(String username) {
        // 사용자 아이디
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되었는지 여부
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠겨있는지 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명이 만료되었는지 여부
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 사용자가 사용 가능한지 여부
        return true;
    }
}