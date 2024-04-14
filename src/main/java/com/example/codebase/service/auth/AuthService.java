package com.example.codebase.service.auth;

import com.example.codebase.dto.UserDto;
import com.example.codebase.dto.request.SignInRequestDto;

/**
 * Auth service.
 */
public interface AuthService {
    UserDto login(SignInRequestDto request);

    UserDto signUp(UserDto request);
}
