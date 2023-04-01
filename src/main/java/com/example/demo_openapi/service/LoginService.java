package com.example.demo_openapi.service;

import com.example.demo_openapi.mapper.UserMapper;
import com.example.demo_openapi.model.KakaoToken;
import com.example.demo_openapi.model.Users;
import com.example.demo_openapi.system.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginService {
    @Autowired
    public UserMapper userMapper;

    @Autowired
    public JwtTokenProvider jwtTokenProvider;
    @Value("${kakao.clientId}")
    private String kClientId;
    @Value("${kakao.secretkey}")
    private  String kSecretKey;

    public String getKakaoToken(String code) throws HttpServerErrorException {
        KakaoToken token = new KakaoToken();

        String request_uri = "https://kauth.kakao.com/oauth/token";
        String redirect_uri = "http://localhost:8080/api/auth/kakao";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("grant_type", "authorization_code");
        param.add("client_id", kClientId);
        param.add("redirect_uri", redirect_uri);
        param.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(param, headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<KakaoToken> res = rt.postForEntity(request_uri, request, KakaoToken.class);

        if(res.getStatusCodeValue() == 200){
            token = res.getBody();

            //user 정보 불러오기
            Users user = this.getUser(token.access_token);

            String id = user.getId().toString();
            String userName = user.kakao_account.profile.getNickname();

            //전달받은 사용자 정보를 기반으로 Token 생성
            String jwtToken = jwtTokenProvider.generateToken(id, userName);

            //TODO :: DB에 저장
            return jwtToken;
        }
        else return null;
    }

    public Users getUser(String token){
        String url = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();

        ResponseEntity<Users> res = rt.exchange(url, HttpMethod.GET, entity, Users.class);
        if(res.getStatusCodeValue() == 200){
            Users user = res.getBody();
            return user;
        }
        else return null;
    }

    public String getGoogleToken(String credential){
        // JWT 디코딩
        String[] credentialParts = credential.split("\\.");
        String body = new String(Base64.getUrlDecoder().decode(credentialParts[1]));

        String jwtToken = "";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> map = objectMapper.readValue(body, new TypeReference<Map<String, Object>>(){});
            String id = map.get("email").toString();
            String userName = map.get("name").toString();

            //전달받은 사용자 정보를 기반으로 Token 생성
            jwtToken = jwtTokenProvider.generateToken(id, userName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jwtToken;
    }
}
