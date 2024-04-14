package com.example.codebase.controller;

import com.example.codebase.constant.Path;
import com.example.codebase.dto.UserDto;
import com.example.codebase.dto.request.SignInRequestDto;
import com.example.codebase.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Base Controller.
 */
@RestController
@RequestMapping(Path.DEFAULT_API_VERSION)
public class BaseController {
    private final AuthService authService;

    public BaseController(final AuthService authService) {
        this.authService = authService;
    }

    public ResponseEntity<UserDto> signup(@RequestBody UserDto request) {
        return ResponseEntity.ok(authService.signUp(request));
    }

    public ResponseEntity<UserDto> login(@RequestBody SignInRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
