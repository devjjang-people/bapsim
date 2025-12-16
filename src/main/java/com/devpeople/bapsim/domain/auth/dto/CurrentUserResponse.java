package com.devpeople.bapsim.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrentUserResponse {
    private Long userId;
    private String email;
    private String role;
}