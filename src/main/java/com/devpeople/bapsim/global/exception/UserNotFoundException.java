package com.devpeople.bapsim.global.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("사용자를 찾을 수 없습니다. id=" + id);
    }
}
