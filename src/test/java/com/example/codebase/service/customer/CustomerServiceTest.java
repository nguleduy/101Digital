package com.example.codebase.service.customer;

import com.example.codebase.dto.MenuDto;
import com.example.codebase.dto.OrderDto;
import com.example.codebase.dto.QueueDto;
import com.example.codebase.dto.ShopDto;
import com.example.codebase.dto.UserDto;
import com.example.codebase.exception.CustomException;
import com.example.codebase.mapper.OrderMapper;
import com.example.codebase.mapper.QueueMapper;
import com.example.codebase.model.Order;
import com.example.codebase.model.Queue;
import com.example.codebase.model.Shop;
import com.example.codebase.repository.OrderRepository;
import com.example.codebase.repository.QueueRepository;
import com.example.codebase.repository.ShopRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomerServiceTest {
    @Mock
    private ShopRepository shopRepository;

    @Mock
    private QueueRepository queueRepository;

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    public CustomerServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findNearestShops_ReturnsNearestShops() {
        Shop shop1 = new Shop(1L, 40.7128, -74.0060);
        Shop shop2 = new Shop(2L, 34.0522, -118.2437);
        Shop shop3 = new Shop(3L, 51.5074, -0.1278);
        List<Shop> mockShops = Arrays.asList(shop1, shop2, shop3);
        when(shopRepository.findAll()).thenReturn(mockShops);

        double latitude = 40.73061;
        double longitude = -73.935242;

        List<ShopDto> expected = Arrays.asList(
                new ShopDto(1L, 40.7128, -74.0060),
                new ShopDto(3L, 51.5074, -0.1278),
                new ShopDto(2L, 34.0522, -118.2437)
        );

        List<ShopDto> actual = customerService.findNearestShops(latitude, longitude);

        assertEquals(expected.size(), actual.size());
    }

    @Test
    void findNearestShops_NoShopsFound() {
        double latitude = 1.0;
        double longitude = 1.0;
        when(shopRepository.findAll()).thenReturn(Collections.emptyList());

        CustomException exception = assertThrows(CustomException.class, () -> customerService.findNearestShops(latitude, longitude));
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getErrorCode());
        assertEquals("There is no shops near by you.", exception.getMessage());
    }

    @Test
    void cancelOrder_ThrowsExceptionIfOrderNotFound() {
        when(queueRepository.findAll()).thenReturn(List.of());

        OrderDto dto = new OrderDto();
        dto.setId(1L);
        dto.setShop(new ShopDto());

        assertThrows(
                CustomException.class,
                () -> customerService.cancelOrder(dto),
                "Order not found"
        );
    }

    @Test
    void placeOrder_ReturnsQueueDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        Order orderEntity = OrderMapper.INSTANCE.toEntity(orderDto);
        when(orderRepository.save(any())).thenReturn(orderEntity);

        ShopDto shopDto = new ShopDto();
        shopDto.setId(1L);
        QueueDto expectedQueueDto = new QueueDto();
        expectedQueueDto.setId(1L);
        when(queueRepository.save(any())).thenReturn(QueueMapper.INSTANCE.toEntity(expectedQueueDto));

        QueueDto actualQueueDto = customerService.placeOrder(orderDto);

        assertEquals(expectedQueueDto, actualQueueDto);
    }

    @Test
    void viewQueueByShop_ReturnsListOfQueueDto() {
        ShopDto shopDto = new ShopDto();
        shopDto.setId(1L);
        QueueDto queueDto = new QueueDto();
        queueDto.setId(1L);
        when(queueRepository.findByShopAndDisabledFalse(any())).thenReturn(Collections.singletonList(QueueMapper.INSTANCE.toEntity(queueDto)));

        List<QueueDto> queueDtoList = customerService.viewQueueByShop(shopDto.getId());

        assertNotNull(queueDtoList);
        assertEquals(1, queueDtoList.size());
        assertEquals(queueDto, queueDtoList.get(0));
    }

    @Test
    void cancelOrder_CancelsOrderAndReturnsQueueDto() {
        Queue queue = new Queue();
        queue.setId(1L);
        queue.setOrder(new Order(1L));
        queue.setShop(new Shop(1L));
        OrderDto dto = new OrderDto();
        dto.setId(1L);
        dto.setMenu(new MenuDto(1L));
        dto.setShop(new ShopDto(1L));
        dto.setUser(new UserDto(1L));
        when(queueRepository.findAll()).thenReturn(List.of(queue));

        QueueDto expectedQueueDto = new QueueDto();
        expectedQueueDto.setId(1L);
        queue.setDisabled(false);
        QueueDto actualQueueDto = customerService.cancelOrder(dto);
        assertTrue(actualQueueDto.getDisabled());
    }
}
