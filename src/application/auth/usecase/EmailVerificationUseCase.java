package application.auth.usecase;

import application.auth.dto.request.EmailVerificationRequest;
import application.auth.service.AuthService;

public class EmailVerificationUseCase {
    private final AuthService authService;

    public EmailVerificationUseCase(AuthService authService) {
        this.authService = authService;
    }

    public void sendVerificationCode(String email) {
        authService.sendEmailVerificationCode(email);
    }

    public boolean verify(EmailVerificationRequest request) {
        return authService.verifyEmailCode(request.getEmail(), request.getVerificationCode());
    }
}
