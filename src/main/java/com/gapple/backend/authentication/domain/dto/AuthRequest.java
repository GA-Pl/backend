package com.gapple.backend.authentication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthRequest {

    private String email;

    private String accessToken;

    private String refreshToken;

    private String displayName;

    private String profileImg;
}
