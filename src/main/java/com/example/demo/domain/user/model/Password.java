package com.example.demo.domain.user.model;

public record Password(String value) {

    private static final int MIN_LENGTH = 8;

    public Password {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
    }

    /**
     * Raw password 검증용 정적 메서드
     * 회원가입 시 사용자가 입력한 비밀번호 유효성 검사
     */
    public static void validateRawPassword(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
        if (rawPassword.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("비밀번호는 최소 " + MIN_LENGTH + "자 이상이어야 합니다.");
        }
    }

    @Override
    public String toString() {
        return "[PROTECTED]";
    }
}


