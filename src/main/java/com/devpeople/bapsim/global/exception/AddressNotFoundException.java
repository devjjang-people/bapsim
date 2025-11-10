package com.devpeople.bapsim.global.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Integer id) {
        super("주소를 찾을 수 없습니다. id=" + id);
    }
}
