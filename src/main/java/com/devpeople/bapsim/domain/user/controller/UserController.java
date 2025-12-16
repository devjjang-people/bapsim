package com.devpeople.bapsim.domain.user.controller;

import com.devpeople.bapsim.domain.auth.dto.CurrentUserResponse;
import com.devpeople.bapsim.domain.user.dto.SignupRequest;
import com.devpeople.bapsim.domain.user.entity.User;
import com.devpeople.bapsim.domain.user.service.UserService;
import com.devpeople.bapsim.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    // TODO 조회조건 추가 (ex. 사용자 식별자)
    @PostMapping("/list")
    public List<User> getUserList(){
        return userService.getUserList();
    }

    /**
     * 회원가입 기능
     * */
    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest request) {
        return userService.signup(request);
    }

    /**
     * 현재 인증된 사용자 정보 조회
     * */
    @GetMapping("/me")
    public CurrentUserResponse getCurrentUser(@AuthenticationPrincipal CustomUserDetails principal) {
        return new CurrentUserResponse(principal.getUserId(), principal.getEmail(), principal.getRoleName());
    }
}
