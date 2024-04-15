package com.example.codebase.service.auth;

import com.example.codebase.dto.UserDto;
import com.example.codebase.dto.request.SignInRequestDto;
import com.example.codebase.exception.CustomException;
import com.example.codebase.mapper.UserMapper;
import com.example.codebase.model.User;
import com.example.codebase.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AuthServiceImpl.
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(final UserRepository userRepository,
                           final BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto login(SignInRequestDto request) {
        User user = userRepository.findByUsername(request.username());
        if (user != null && passwordEncoder.matches(request.password(), user.getPassword())) {
            log.info("Login successful.");
            return UserMapper.INSTANCE.toDto(user);
        }

        log.error("User not found");
        throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "User not found");
    }

    @Override
    public UserDto signUp(UserDto request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user != null) {
            log.error("Username already exists");
            throw new CustomException(HttpStatus.CONFLICT.value(), "Username already exists");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);

        User savedUser = userRepository.save(UserMapper.INSTANCE.toEntity(request));

        log.info("Signup successful.");
        return UserMapper.INSTANCE.toDto(savedUser);
    }
}
