package com.example.codebase.service.customer;

import com.example.codebase.dto.OrderDto;
import com.example.codebase.dto.QueueDto;
import com.example.codebase.dto.ShopDto;
import java.util.List;

/**
 * Customer service.
 */
public interface CustomerService {
    List<ShopDto> findNearestShops(double latitude, double longitude);

    QueueDto placeOrder(OrderDto dto);

    List<QueueDto> viewQueueByShop(Long shopId);

    QueueDto cancelOrder(OrderDto dto);
}
