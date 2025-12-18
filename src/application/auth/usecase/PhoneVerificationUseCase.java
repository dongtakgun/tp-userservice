package application.auth.usecase;

import application.auth.dto.request.PhoneVerificationRequest;
import application.auth.service.AuthService;

public class PhoneVerificationUseCase {
    private final AuthService authService;

    public PhoneVerificationUseCase(AuthService authService) {
        this.authService = authService;
    }

    public void sendVerificationCode(String phoneNumber) {
        authService.sendPhoneVerificationCode(phoneNumber);
    }

    public boolean verify(PhoneVerificationRequest request) {
        return authService.verifyPhoneCode(request.getPhoneNumber(), request.getVerificationCode());
    }
}
