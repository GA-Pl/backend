package com.gapple.backend.authentication.service;

import com.gapple.backend.authentication.domain.dto.NaverUserProfile;
import com.gapple.backend.authentication.domain.dto.OAuthNaverUserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OAuthService {

    WebClient webClient;

    public NaverUserProfile sendNaverUserProfileRequest(String socialAccessToken) {

        return webClient.get()
                .uri("https://openapi.naver.com/v1/nid/me")
                .header("Authorization", "Bearer" + socialAccessToken)
                .retrieve()
                .bodyToMono(OAuthNaverUserProfileResponse.class)
                .block()
                .getResponse();
    }
}
