package com.auth.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.app.model.token.EmailVerificationToken;

import java.util.Optional;

public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {

    Optional<EmailVerificationToken> findByToken(String token);
}
