package com.example.codebase.dto;

import lombok.Data;

/**
 * Promotion dto.
 */
@Data
public class PromotionDto {
    private Long id;
    private ShopDto shop;
    private UserDto user;
    private String description;
    private Integer points;
    private Boolean disabled;
}
