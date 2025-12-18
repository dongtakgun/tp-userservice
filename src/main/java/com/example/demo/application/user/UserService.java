package com.example.demo.application.user;

import com.example.demo.application.user.dto.LoginCommand;
import com.example.demo.application.user.dto.SignUpCommand;
import com.example.demo.application.user.dto.UserResponse;
import com.example.demo.domain.user.model.Email;
import com.example.demo.domain.user.model.Password;
import com.example.demo.domain.user.model.User;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    @Transactional
    public UserResponse signUp(SignUpCommand command) {
        // 1. 이메일 형식 검증 (Email 생성 시 자동 검증)
        Email email = new Email(command.email());

        // 2. 이메일 중복 체크
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + command.email());
        }

        // 3. 비밀번호 유효성 검증 (8자 이상)
        Password.validateRawPassword(command.password());

        // 4. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(command.password());
        Password password = new Password(encodedPassword);

        // 5. User 도메인 객체 생성
        User user = User.create(email, password, command.country(), command.phoneNumber());

        // 6. 저장
        User savedUser = userRepository.save(user);

        // 7. 응답 반환
        return UserResponse.from(savedUser);
    }

    /**
     * 로그인
     */
    public UserResponse login(LoginCommand command) {
        // 1. 이메일로 사용자 조회
        Email email = new Email(command.email());
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));

        // 2. 비밀번호 검증
        boolean isPasswordMatch = user.matchPassword(
                command.password(),
                passwordEncoder::matches
        );

        if (!isPasswordMatch) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        // 3. 응답 반환 (JWT는 2단계에서 추가)
        return UserResponse.from(user);
    }
}


