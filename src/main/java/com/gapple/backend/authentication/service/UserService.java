package com.gapple.backend.authentication.service;

import com.gapple.backend.authentication.domain.dto.AuthRequest;
import com.gapple.backend.authentication.domain.dto.NaverUserProfile;
import com.gapple.backend.authentication.domain.entity.User;
import com.gapple.backend.authentication.repository.UserRepository;
import com.gapple.backend.common.exception.CustomException;
import com.gapple.backend.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User get(NaverUserProfile profile) {

        return userRepository.findByEmailAndSocialId(profile.getEmail(), profile.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
    }

    @Transactional
    public User save(AuthRequest dto, NaverUserProfile profile) {

        User user = User.builder()
                .email(dto.getEmail())
                .displayName(dto.getDisplayName())
                .socialId(profile.getId())
                .profileImg(dto.getProfileImg())
                .build();

        return userRepository.save(user);
    }

    public boolean isExistsByEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    public boolean isExistsByIdAndEmail(NaverUserProfile profile) {

        return userRepository.existsByEmailAndSocialId(profile.getEmail(), profile.getId());
    }
}
