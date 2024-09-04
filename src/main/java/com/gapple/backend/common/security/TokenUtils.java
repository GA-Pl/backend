package com.gapple.backend.common.security;

import com.gapple.backend.authentication.domain.entity.User;
import com.gapple.backend.common.exception.CustomException;
import com.gapple.backend.common.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
public class TokenUtils {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.accessToken.expiration}")
    private Long ACCESS_TOKEN_EXPIRATION;

    @Value("${jwt.refreshToken.expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;

    public String createAccessToken(User user) {

        Date now = new Date();

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(ACCESS_TOKEN_EXPIRATION).toMillis()))
                .setSubject(String.valueOf(user.getId()))
                .claim("email", user.getEmail())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String createRefreshToken(User user) {

        Date now = new Date();

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(REFRESH_TOKEN_EXPIRATION).toMillis()))
                .setSubject(String.valueOf(user.getId()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private Claims parseToken(String token) {

        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

    public Long getUserId(String token) {

        Claims claims = parseToken(token);
        return Long.valueOf(claims.getSubject());
    }

    public boolean isExpired(String token) {

        Claims claims = parseToken(token);
        return claims.getExpiration().before(new Date());
    }
}
