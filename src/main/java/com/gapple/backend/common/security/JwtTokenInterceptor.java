package com.gapple.backend.common.security;

import com.gapple.backend.common.exception.CustomException;
import com.gapple.backend.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

    private final TokenUtils tokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String header = request.getHeader("Authorization");
        String token = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        if (token == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        if (tokenUtils.isExpired(token)) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        }

        return true;
    }
}
