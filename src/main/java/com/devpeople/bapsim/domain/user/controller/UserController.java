package com.devpeople.bapsim.domain.user.controller;

import com.devpeople.bapsim.domain.user.dto.SignupRequest;
import com.devpeople.bapsim.domain.user.entity.User;
import com.devpeople.bapsim.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
