package com.example.demo_openapi.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContoller {
    @GetMapping("/")
    public String main(){
        return "/index";
    }
}
