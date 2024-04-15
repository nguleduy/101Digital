package com.example.codebase.controller.customer;

import com.example.codebase.dto.OrderDto;
import com.example.codebase.dto.QueueDto;
import com.example.codebase.dto.ShopDto;
import com.example.codebase.dto.UserDto;
import com.example.codebase.dto.request.SignInRequestDto;
import com.example.codebase.service.auth.AuthService;
import com.example.codebase.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @Mock
    private AuthService authService;

    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerController = new CustomerController(customerService, authService);
    }

    @Test
    void signup_ReturnsUserDto() {
        UserDto request = new UserDto();
        request.setUsername("testUser");
        request.setPassword("testPassword");

        when(authService.signUp(request)).thenReturn(new UserDto());

        ResponseEntity<UserDto> response = customerController.signup(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void login_ReturnsUserDto() {
        SignInRequestDto request = new SignInRequestDto("testUser", "testPassword");

        when(authService.login(request)).thenReturn(new UserDto());

        ResponseEntity<UserDto> response = customerController.login(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findNearestShops_ReturnsListOfShopDto() {
        double latitude = 40.7128;
        double longitude = -74.0060;

        when(customerService.findNearestShops(latitude, longitude)).thenReturn(Collections.singletonList(new ShopDto()));

        ResponseEntity<List<ShopDto>> response = customerController.findNearestShops(latitude, longitude);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void placeOrder_Successful() {
        OrderDto orderDto = new OrderDto(1L);
        QueueDto expectedQueueDto = new QueueDto(1L);
        when(customerService.placeOrder(any())).thenReturn(expectedQueueDto);

        ResponseEntity<QueueDto> response = customerController.placeOrder(orderDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedQueueDto, response.getBody());
        verify(customerService, times(1)).placeOrder(orderDto);
    }

    @Test
    void viewQueueByShop_Successful() {
        Long shopId = 1L;
        List<QueueDto> expectedQueueList = Collections.singletonList(new QueueDto(1L));
        when(customerService.viewQueueByShop(shopId)).thenReturn(expectedQueueList);

        ResponseEntity<List<QueueDto>> response = customerController.viewQueueByShop(shopId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedQueueList, response.getBody());
        verify(customerService, times(1)).viewQueueByShop(shopId);
    }

    @Test
    void cancelOrder_Successful() {
        OrderDto orderDto = new OrderDto(1L);
        QueueDto expectedQueueDto = new QueueDto(1L);
        when(customerService.cancelOrder(any())).thenReturn(expectedQueueDto);

        ResponseEntity<QueueDto> response = customerController.cancelOrder(orderDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedQueueDto, response.getBody());
        verify(customerService, times(1)).cancelOrder(orderDto);
    }
}
