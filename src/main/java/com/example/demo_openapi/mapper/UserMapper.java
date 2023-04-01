package com.example.demo_openapi.mapper;

import com.example.demo_openapi.model.AuthLogin;
import com.example.demo_openapi.model.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    Users selectById(int id);

    int insLoginProvide(AuthLogin params);
}