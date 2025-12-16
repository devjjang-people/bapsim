package com.devpeople.bapsim.domain.auth.service;

import com.devpeople.bapsim.domain.auth.dto.CurrentUserResponse;
import com.devpeople.bapsim.domain.auth.dto.LoginResponse;
import com.devpeople.bapsim.domain.auth.entity.RefreshToken;
import com.devpeople.bapsim.domain.auth.repository.RefreshTokenRepository;
import com.devpeople.bapsim.domain.user.entity.User;
import com.devpeople.bapsim.domain.user.repository.UserRepository;
import com.devpeople.bapsim.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    private static final long REFRESH_EXPIRE_SECONDS = 60 * 60 * 24 * 14; // 14일

    /**
     * 로그인
     */
    public LoginResponse login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        String role = user.getRole().name();

        String accessToken = tokenProvider.createAccessToken(
                user.getId(), user.getEmail(), role
        );

        String refreshToken = tokenProvider.createRefreshToken(
                user.getId(), user.getEmail(), role
        );

        saveOrUpdateRefreshToken(user.getId(), refreshToken);

        return new LoginResponse(accessToken, refreshToken);
    }

    /**
     * 토큰 재발급
     */
    public LoginResponse refresh(String refreshToken) {
        var jws = tokenProvider.parse(refreshToken);

        if (!tokenProvider.isRefreshToken(jws)) {
            throw new IllegalArgumentException("Refresh Token이 아닙니다.");
        }

        RefreshToken savedToken = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 Refresh Token입니다."));

        Long userId = tokenProvider.getUserId(jws);
        String email = tokenProvider.getEmail(jws);
        String role = tokenProvider.getRole(jws);

        String newAccessToken = tokenProvider.createAccessToken(userId, email, role);
        String newRefreshToken = tokenProvider.createRefreshToken(userId, email, role);

        savedToken.rotate(
                newRefreshToken,
                LocalDateTime.now().plusSeconds(REFRESH_EXPIRE_SECONDS)
        );

        return new LoginResponse(newAccessToken, newRefreshToken);
    }

    /**
     * 로그아웃
     */
    public void logout(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }

    /**
     * 현재 인증 사용자 정보
     * (DB 조회 없는 버전)
     */
    public CurrentUserResponse getCurrentUser(Long userId, String email, String role) {
        return new CurrentUserResponse(userId, email, role);
    }

    private void saveOrUpdateRefreshToken(Long userId, String refreshToken) {
        refreshTokenRepository.findByUserId(userId)
                .ifPresentOrElse(
                        token -> token.rotate(
                                refreshToken,
                                LocalDateTime.now().plusSeconds(REFRESH_EXPIRE_SECONDS)
                        ),
                        () -> refreshTokenRepository.save(
                                RefreshToken.builder()
                                        .userId(userId)
                                        .token(refreshToken)
                                        .expiresAt(LocalDateTime.now().plusSeconds(REFRESH_EXPIRE_SECONDS))
                                        .build()
                        )
                );
    }
}
