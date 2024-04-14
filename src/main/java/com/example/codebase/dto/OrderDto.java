package com.example.codebase.dto;

import java.time.LocalTime;
import lombok.Data;

/**
 * Order dto.
 */
@Data
public class OrderDto {
    private Long id;
    private UserDto user;
    private MenuDto menu;
    private ShopDto shop;
    private LocalTime orderTime;
    private String status;
    private Boolean disabled;
}
