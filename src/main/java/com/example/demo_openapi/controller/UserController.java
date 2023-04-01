package com.example.demo_openapi.controller;

import com.example.demo_openapi.model.Users;
import com.example.demo_openapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public Users getUser(@PathVariable int id) {
        return userService.getUser(id);
    }
}
