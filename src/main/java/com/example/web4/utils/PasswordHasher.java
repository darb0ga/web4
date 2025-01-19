package com.example.web4.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class PasswordHasher {
    private static final int BCRYPT_STRENGTH = 11;

    public String digestPassword(String plainTextPassword) {
        try {
            return encoder().encode(plainTextPassword);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при энкодинге пароля", e);
        }
    }

    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] passwordDigest = digest.digest();
            return new String(Base64.getEncoder().encode(passwordDigest));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка при хешировании пароля", e);
        }
    }

    @Bean
    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(BCRYPT_STRENGTH);
    }
}