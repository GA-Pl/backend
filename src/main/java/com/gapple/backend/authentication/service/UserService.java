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

    public User get(Long id) {

        return userRepository.findById(id)
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

    public boolean checkExistsByEmail(String email) {

        boolean isUserExist = userRepository.existsByEmail(email);

        if (isUserExist) {
            return true;
        } else {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
    }

    public boolean isExistsByIdAndEmail(NaverUserProfile profile) {

        return userRepository.existsByEmailAndSocialId(profile.getEmail(), profile.getId());
    }
}
