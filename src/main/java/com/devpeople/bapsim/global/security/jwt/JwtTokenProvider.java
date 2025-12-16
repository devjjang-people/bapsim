package com.devpeople.bapsim.global.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private final SecretKey key;
    private final String issuer;
    private final long accessExpSeconds;
    private final long refreshExpSeconds;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.issuer}") String issuer,
            @Value("${jwt.access-exp-seconds}") long accessExpSeconds,
            @Value("${jwt.refresh-exp-seconds}") long refreshExpSeconds
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.issuer = issuer;
        this.accessExpSeconds = accessExpSeconds;
        this.refreshExpSeconds = refreshExpSeconds;
    }

    public String createAccessToken(Long userId, String email, String role) {
        return createToken(userId, email, role, accessExpSeconds, "access");
    }

    public String createRefreshToken(Long userId, String email, String role) {
        return createToken(userId, email, role, refreshExpSeconds, "refresh");
    }

    private String createToken(Long userId, String email, String role, long expSeconds, String type) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(expSeconds);

        return Jwts.builder()
                .issuer(issuer)
                .subject(String.valueOf(userId))
                .claims(Map.of(
                        "email", email,
                        "role", role,
                        "typ", type
                ))
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .requireIssuer(issuer)
                .build()
                .parseSignedClaims(token);
    }

    public boolean isRefreshToken(Jws<Claims> jws) {
        return "refresh".equals(jws.getPayload().get("typ", String.class));
    }

    public Long getUserId(Jws<Claims> jws) {
        return Long.valueOf(jws.getPayload().getSubject());
    }

    public String getEmail(Jws<Claims> jws) {
        return jws.getPayload().get("email", String.class);
    }

    public String getRole(Jws<Claims> jws) {
        return jws.getPayload().get("role", String.class);
    }
}
