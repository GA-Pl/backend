package com.gapple.backend.authentication.repository;

import com.gapple.backend.authentication.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndSocialId(String email, String socialId);

    Optional<User> findByEmailAndSocialId(String email, String socialId);
}
