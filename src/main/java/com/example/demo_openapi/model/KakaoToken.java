package com.example.demo_openapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoToken {
    public String token_type;
    public String access_token;
    public int expires_in;
    public String refresh_token;
    public int refresh_token_expires_in;

}

