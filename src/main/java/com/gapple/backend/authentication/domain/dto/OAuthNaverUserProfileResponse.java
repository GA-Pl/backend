package com.gapple.backend.authentication.domain.dto;

import lombok.Getter;

@Getter
public class OAuthNaverUserProfileResponse {

    private String resultCode;

    private String message;

    private NaverUserProfile response;
}
