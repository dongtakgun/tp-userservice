package com.example.demo.infrastructure.persistence.user;

import com.example.demo.domain.user.model.Email;
import com.example.demo.domain.user.model.User;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = UserEntity.from(user);       // Domain → Entity
        UserEntity savedEntity = jpaUserRepository.save(entity);
        return savedEntity.toDomain();                    // Entity → Domain
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return jpaUserRepository.findByEmail(email.value())
                .map(UserEntity::toDomain);               // Entity → Domain
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaUserRepository.existsByEmail(email.value());
    }
}
