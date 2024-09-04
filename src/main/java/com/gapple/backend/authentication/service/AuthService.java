package com.gapple.backend.authentication.service;

import com.gapple.backend.authentication.domain.dto.AuthRequest;
import com.gapple.backend.authentication.domain.dto.AuthResponse;
import com.gapple.backend.authentication.domain.dto.NaverUserProfile;
import com.gapple.backend.authentication.domain.entity.User;
import com.gapple.backend.common.exception.CustomException;
import com.gapple.backend.common.exception.ErrorCode;
import com.gapple.backend.common.security.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final OAuthService oAuthService;

    private final TokenUtils tokenUtils;

    @Transactional
    public AuthResponse signUpUser(AuthRequest dto) {

        NaverUserProfile profile = oAuthService.sendNaverUserProfileRequest(dto.getAccessToken());

        if (userService.isExistsByIdAndEmail(profile)) {
            throw new CustomException(ErrorCode.CONFLICT_RESOURCE);
        }

        User user = userService.save(dto, profile);

        return buildResponse(user);
    }

    public AuthResponse logInUser(AuthRequest dto) {

        NaverUserProfile profile = oAuthService.sendNaverUserProfileRequest(dto.getAccessToken());

        User user = userService.get(profile);

        return buildResponse(user);
    }

    public AuthResponse refreshAuthInfo(String refreshToken) {

        if (refreshToken != null && refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        } else {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        if (tokenUtils.isExpired(refreshToken)) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        }

        Long id = tokenUtils.getUserId(refreshToken);
        User user = userService.get(id);

        return buildResponse(user);
    }

    private AuthResponse buildResponse(User user) {

        return AuthResponse.builder()
                .email(user.getEmail())
                .profileImg(user.getProfileImg())
                .accessToken(tokenUtils.createAccessToken(user))
                .refreshToken(tokenUtils.createRefreshToken(user))
                .build();
    }
}
