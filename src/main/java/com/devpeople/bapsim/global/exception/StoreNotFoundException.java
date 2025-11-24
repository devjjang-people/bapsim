package com.devpeople.bapsim.global.exception;

public class StoreNotFoundException extends RuntimeException {
    public StoreNotFoundException(Long id) {
        super("가게를 찾을 수 없습니다. id=" + id);
    }
}
