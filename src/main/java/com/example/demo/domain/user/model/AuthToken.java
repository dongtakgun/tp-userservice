package com.example.demo.domain.user.model;

public record AuthToken(
        String accessToken,
        String refreshToken
) {

}