package com.ddareungi.bike_share.oauth;

import com.ddareungi.bike_share.user.User;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuth2Attribute {

    private Map<String, Object> attributes;
    private String attributeKey;
    private String email;
    private String nickname;
    private String profileImageUrl;

    @Builder
    public OAuth2Attribute(Map<String, Object> attributes, String attributeKey, String email, String nickname, String profileImageUrl) {
        this.attributes = attributes;
        this.attributeKey = attributeKey;
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public static OAuth2Attribute of(String registrationId, Map<String, Object> attributes) {
        if ("kakao".equals(registrationId)) {
            return ofKakao(attributes);
        }
        return ofKakao(attributes);
    }

    private static OAuth2Attribute ofKakao(Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuth2Attribute.builder()
                .nickname((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .profileImageUrl((String) kakaoProfile.get("profile_image_url"))
                .attributes(attributes)
                .attributeKey("id")
                .build();
    }

    public User toEntity() {
        return new User(email, nickname, profileImageUrl);
    }
}
