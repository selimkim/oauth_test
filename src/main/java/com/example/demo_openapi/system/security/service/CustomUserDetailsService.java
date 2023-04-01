package com.example.demo_openapi.system.security.service;

import com.example.demo_openapi.system.security.model.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String user = "";
        if (user == null) {
            throw new UsernameNotFoundException("사용자 없음 " + email);
        }

        CustomUserDetails customUserDetails = new CustomUserDetails();


        return customUserDetails;
    }
}