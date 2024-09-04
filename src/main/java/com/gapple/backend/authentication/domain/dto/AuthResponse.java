package com.gapple.backend.authentication.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {

    private String accessToken;

    private String refreshToken;

    private String email;

    private String profileImg;
}
