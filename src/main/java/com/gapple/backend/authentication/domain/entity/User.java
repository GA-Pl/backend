package com.gapple.backend.authentication.domain.entity;

import com.gapple.backend.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String displayName;

    private String nickName;

    private String profileImg;

    private String socialId;

    private String refreshToken;

    private LocalDateTime lastLoggedInAt;
}
