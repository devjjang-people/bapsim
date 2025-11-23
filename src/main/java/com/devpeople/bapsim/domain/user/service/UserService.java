package com.devpeople.bapsim.domain.user.service;

import com.devpeople.bapsim.domain.store.entity.Store;
import com.devpeople.bapsim.domain.user.entity.User;
import com.devpeople.bapsim.domain.user.repository.UserRepository;
import com.devpeople.bapsim.global.exception.StoreNotFoundException;
import com.devpeople.bapsim.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Integer id) {

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getUserList() {

        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setStatus("WITHDRAWN"); // 삭제

        return userRepository.save(user);
    }
}
