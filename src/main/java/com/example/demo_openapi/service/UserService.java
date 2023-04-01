package com.example.demo_openapi.service;

import com.example.demo_openapi.mapper.UserMapper;
import com.example.demo_openapi.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    public Users getUser(int id) {
        return userMapper.selectById(id);
    }
}