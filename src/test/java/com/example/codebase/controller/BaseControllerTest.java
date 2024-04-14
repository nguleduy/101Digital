package com.example.codebase.controller;

import com.example.codebase.controller.BaseController;
import com.example.codebase.dto.UserDto;
import com.example.codebase.dto.request.SignInRequestDto;
import com.example.codebase.service.auth.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BaseControllerTest {
    @Mock
    private AuthService authService;

    private BaseController baseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        baseController = new BaseController(authService);
    }

    @Test
    void signup_ReturnsUserDto() {
        UserDto request = new UserDto();
        request.setUsername("testUser");
        request.setPassword("testPassword");

        when(authService.signUp(request)).thenReturn(new UserDto());

        ResponseEntity<UserDto> response = baseController.signup(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void login_ReturnsUserDto() {
        SignInRequestDto request = new SignInRequestDto("testUser", "testPassword");

        when(authService.login(request)).thenReturn(new UserDto());

        ResponseEntity<UserDto> response = baseController.login(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
