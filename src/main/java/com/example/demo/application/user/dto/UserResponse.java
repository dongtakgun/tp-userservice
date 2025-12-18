package com.example.demo.application.user.dto;

import com.example.demo.domain.user.model.User;

public record UserResponse(
        Long id,
        String email,
        String country
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail().value(),
                user.getCountry()
        );
    }
}


