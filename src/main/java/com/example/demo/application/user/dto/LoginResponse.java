package com.example.demo.application.user.dto;

import com.example.demo.domain.user.model.AuthToken;
import com.example.demo.domain.user.model.User;

public record LoginResponse(
        Long id,
        String email,
        String name,
        String accessToken,
        String refreshToken
) {
    public static LoginResponse of(User user, AuthToken authToken) {
        return new LoginResponse(
                user.getId(),
                user.getEmail().value(),
                user.getName(),
                authToken.accessToken(),
                authToken.refreshToken()
        );
    }
}