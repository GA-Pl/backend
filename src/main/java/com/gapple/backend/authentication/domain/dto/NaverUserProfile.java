package com.gapple.backend.authentication.domain.dto;

import lombok.Getter;

@Getter
public class NaverUserProfile {

    private String id;

    private String nickname;

    private String name;

    private String email;

    private String gender;

    private String age;

    private String birthday;

    private String profile_image;

    private String birthyear;

    private String mobile;
}
