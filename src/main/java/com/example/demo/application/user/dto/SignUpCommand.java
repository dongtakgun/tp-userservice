package com.example.demo.application.user.dto;

public record SignUpCommand(
        String name,
        String email,
        String password,
        String country,
        String phoneNumber
) {
}


