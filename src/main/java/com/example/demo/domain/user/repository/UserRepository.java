package com.example.demo.domain.user.repository;

import com.example.demo.domain.user.model.Email;
import com.example.demo.domain.user.model.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(Email email);

    boolean existsByEmail(Email email);
}


