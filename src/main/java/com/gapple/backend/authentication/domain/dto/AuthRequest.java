package com.gapple.backend.authentication.domain.dto;

import lombok.Getter;

@Getter
public class AuthRequest {

    private String email;

    private String accessToken;

    private String refreshToken;

    private String displayName;

    private String profileImg;
}
