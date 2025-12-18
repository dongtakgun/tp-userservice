package com.example.demo.application.user.dto;

public record LoginCommand(
        String email,
        String password
) {
}


