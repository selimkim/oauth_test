package com.example.demo_openapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Users {
    public Long id;  // 사용자 고유 ID
    public KakaoAccount kakao_account;  // 사용자 카카오계정 정보

    @Getter
    @Setter
    public class KakaoAccount{
        public Profile profile;
        public Boolean profile_needs_agreement;  // 사용자 프로필 정보 제공 동의 여부
        public Boolean email_needs_agreement;  // 사용자 이메일 정보 제공 동의 여부
        public Boolean is_email_valid;  // 사용자 이메일이 인증되었는지 여부
        public Boolean is_email_verified;  // 사용자 이메일이 검증된 이메일인지 여부
        public String email;  // 사용자 이메일

        @Getter
        @Setter
        public class Profile {
            public String nickname;       // 사용자 닉네임
            public String profile_image;  // 사용자 프로필 이미지 URL
            public String thumbnail_image;  // 사용자 썸네일 이미지 URL
        }
    }


}

