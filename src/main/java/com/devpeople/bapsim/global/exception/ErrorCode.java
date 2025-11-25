package com.devpeople.bapsim.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(404, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    STORE_NOT_FOUND(404, "STORE_NOT_FOUND", "가게를 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND(404, "PRODUCT_NOT_FOUND", "상품을 찾을 수 없습니다."),
    ADDRESS_NOT_FOUND(404, "ADDRESS_NOT_FOUND", "주소를 찾을 수 없습니다."),

    // 공통 비즈니스 에러
    INVALID_REQUEST(400, "INVALID_REQUEST", "잘못된 요청입니다."),
    UNAUTHORIZED(401, "UNAUTHORIZED", "로그인이 필요합니다.");

    private final int status;
    private final String code;
    private final String message;
}
