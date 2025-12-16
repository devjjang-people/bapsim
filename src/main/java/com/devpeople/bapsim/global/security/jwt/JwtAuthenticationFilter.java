package com.devpeople.bapsim.global.security.jwt;

import com.devpeople.bapsim.global.security.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String bearer = request.getHeader("Authorization");
        String token = resolveBearerToken(bearer);

        if (token != null) {
            try {
                Jws<Claims> jws = tokenProvider.parse(token);

                // access token만 인증에 사용 (refresh는 /auth/refresh에서만 사용)
                String typ = jws.getPayload().get("typ", String.class);
                if ("access".equals(typ)) {
                    String email = tokenProvider.getEmail(jws);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                    var auth = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                // 토큰이 이상하면 인증 없이 진행(= 401은 Security가 처리)
                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(request, response);
    }

    private String resolveBearerToken(String bearer) {
        if (!StringUtils.hasText(bearer)) return null;
        if (!bearer.startsWith("Bearer ")) return null;
        String token = bearer.substring(7);
        return StringUtils.hasText(token) ? token : null;
    }
}
