package com.gapple.backend.authentication.service;

import com.gapple.backend.authentication.domain.dto.AuthRequest;
import com.gapple.backend.authentication.domain.dto.NaverUserProfile;
import com.gapple.backend.common.exception.CustomException;
import com.gapple.backend.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final OAuthService oAuthService;

    public void signUpUser(AuthRequest dto) {

        NaverUserProfile profile = oAuthService.sendNaverUserProfileRequest(dto.getAccessToken());

        if (userService.isExistsByIdAndEmail(profile)) {
            throw new CustomException(ErrorCode.CONFLICT_RESOURCE);
        }

        userService.save(dto, profile);
    }

    public void logInUser(AuthRequest dto) {

        NaverUserProfile profile = oAuthService.sendNaverUserProfileRequest(dto.getAccessToken());

        userService.get(profile);
    }
}