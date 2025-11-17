package com.devpeople.bapsim.domain.user.controller;

import com.devpeople.bapsim.domain.user.entity.User;
import com.devpeople.bapsim.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // TODO 조회조건 추가 (ex. 사용자 식별자)
    @PostMapping("/userList")
    public List<User> getUserList(){
        return userService.getUserList();
    }
}
