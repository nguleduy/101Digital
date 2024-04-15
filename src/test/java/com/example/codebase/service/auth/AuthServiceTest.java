package com.example.codebase.service.auth;

import com.example.codebase.dto.UserDto;
import com.example.codebase.dto.request.SignInRequestDto;
import com.example.codebase.exception.CustomException;
import com.example.codebase.model.User;
import com.example.codebase.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    public AuthServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_Successful() {
        SignInRequestDto request = new SignInRequestDto("username", "password");
        User user = new User("username", "password");
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        UserDto userDto = authService.login(request);

        assertNotNull(userDto);
        assertEquals("username", userDto.getUsername());
    }

    @Test
    void login_UserNotFound() {
        SignInRequestDto request = new SignInRequestDto("username", "password");
        when(userRepository.findByUsername(anyString())).thenReturn(null);

        assertThrows(CustomException.class, () -> authService.login(request));
    }

    @Test
    void signUp_Successful() {
        UserDto request = new UserDto("username", "password");
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        UserDto userDto = authService.signUp(request);

        assertNotNull(userDto);
        assertEquals("username", userDto.getUsername());
    }

    @Test
    void signUp_UsernameExists() {
        UserDto request = new UserDto("existingUsername", "password");
        when(userRepository.findByUsername(anyString())).thenReturn(new User("existingUsername", "password"));

        assertThrows(CustomException.class, () -> authService.signUp(request));
    }
}
