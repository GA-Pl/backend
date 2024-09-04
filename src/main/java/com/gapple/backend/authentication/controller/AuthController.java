package com.gapple.backend.authentication.controller;

import com.gapple.backend.authentication.domain.dto.AuthRequest;
import com.gapple.backend.authentication.domain.dto.AuthResponse;
import com.gapple.backend.authentication.service.AuthService;
import com.gapple.backend.authentication.service.UserService;
import com.gapple.backend.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    @GetMapping("/exists")
    public ResponseEntity<ApiResponse<Boolean>> checkUserExists(
            @RequestParam(value = "email") String email
    ) {

        return ResponseEntity.ok(ApiResponse.success(userService.isExistsByEmail(email)));
    }

    @PostMapping("/oauth/signup")
    public ResponseEntity<ApiResponse<AuthResponse>> signUp(
            @RequestBody AuthRequest dto
    ) {

        AuthResponse authResponse = authService.signUpUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(authResponse));
    }

    @PostMapping("/oauth/login")
    public ResponseEntity<ApiResponse<AuthResponse>> logIn(
            @RequestBody AuthRequest dto
    ) {

        AuthResponse authResponse = authService.logInUser(dto);
        return ResponseEntity.ok(ApiResponse.success(authResponse));
    }

    @PutMapping("/token")
    public ResponseEntity<ApiResponse<AuthResponse>> refreshToken(
            @RequestHeader(value = "Authorization") String refreshToken
    ) {

        AuthResponse authResponse = authService.refreshAuthInfo(refreshToken);
        return ResponseEntity.ok(ApiResponse.success(authResponse));
    }
}
