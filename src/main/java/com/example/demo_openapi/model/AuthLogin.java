package com.example.demo_openapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLogin {
    public String id;
    public String user_id;
    public String provider;
    public String provider_id;
    public String provider_token;
}
