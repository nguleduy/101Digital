package com.example.codebase.controller.shop;

import com.example.codebase.constant.UserType;
import com.example.codebase.dto.MenuDto;
import com.example.codebase.dto.QueueDto;
import com.example.codebase.dto.ShopDto;
import com.example.codebase.dto.UserDto;
import com.example.codebase.dto.request.SignInRequestDto;
import com.example.codebase.service.MenuService;
import com.example.codebase.service.QueueService;
import com.example.codebase.service.ShopService;
import com.example.codebase.service.auth.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShopControllerTest {
    @Mock
    private AuthService authService;

    @Mock
    private ShopService shopService;

    @Mock
    private MenuService menuService;

    @Mock
    private QueueService queueService;

    @InjectMocks
    private ShopController shopController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shopController = new ShopController(authService, shopService, menuService, queueService);
    }

    @Test
    void setupShop_Successful() {
        ShopDto shopDto = new ShopDto(1L);
        ShopDto expectedShopDto = new ShopDto(1L);
        when(shopService.setupShop(any())).thenReturn(expectedShopDto);

        ResponseEntity<ShopDto> response = shopController.setupShop(shopDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedShopDto, response.getBody());
        verify(shopService, times(1)).setupShop(shopDto);
    }

    @Test
    void manageMenu_Successful() {
        MenuDto menuDto = new MenuDto(1L);
        MenuDto expectedMenuDto = new MenuDto(1L);
        when(menuService.manageMenu(any())).thenReturn(expectedMenuDto);

        ResponseEntity<MenuDto> response = shopController.manageMenu(menuDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMenuDto, response.getBody());
        verify(menuService, times(1)).manageMenu(menuDto);
    }

    @Test
    void configureQueue_Successful() {
        QueueDto queueDto = new QueueDto(1L);
        QueueDto expectedQueueDto = new QueueDto(1L);
        when(queueService.configureQueue(any())).thenReturn(expectedQueueDto);

        ResponseEntity<QueueDto> response = shopController.configureQueue(queueDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedQueueDto, response.getBody());
        verify(queueService, times(1)).configureQueue(queueDto);
    }

    @Test
    void signup_Successful() {
        UserDto requestDto = new UserDto(1L);
        UserDto expectedDto = new UserDto(1L);
        expectedDto.setType(UserType.SHOP_OPERATOR.name());
        when(authService.signUp(requestDto)).thenReturn(expectedDto);

        ResponseEntity<UserDto> response = shopController.signup(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
        verify(authService, times(1)).signUp(requestDto);
    }

    @Test
    void login_Successful() {
        SignInRequestDto requestDto = new SignInRequestDto("username", "password");
        UserDto expectedDto = new UserDto(1L);
        when(authService.login(requestDto)).thenReturn(expectedDto);

        ResponseEntity<UserDto> response = shopController.login(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
        verify(authService, times(1)).login(requestDto);
    }
}
