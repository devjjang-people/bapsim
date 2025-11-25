package com.devpeople.bapsim.domain.user.service;

import com.devpeople.bapsim.domain.user.dto.SignupRequest;
import com.devpeople.bapsim.domain.user.entity.User;
import com.devpeople.bapsim.domain.user.repository.UserRepository;
import com.devpeople.bapsim.global.exception.CustomException;
import com.devpeople.bapsim.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User getUserById(Long id) {

        return userRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    public List<User> getUserList() {

        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        user.setStatus("WITHDRAWN"); // 삭제

        return userRepository.save(user);
    }

    /**
     * 회원가입 기능
     */
    public User signup(SignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .email(request.getEmail())
                .password(encryptedPassword)
                .name(request.getName())
                .phone(request.getPhone())
                .role("USER")          // 기본 권한
                .status("ACTIVE")      // 기본 상태
                .build();

        return userRepository.save(user);
    }

}
