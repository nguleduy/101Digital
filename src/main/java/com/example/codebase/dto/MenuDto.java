package com.example.codebase.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 * Menu dto.
 */
@Data
public class MenuDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Boolean disabled;

    public MenuDto(Long id) {
        this.id = id;
    }
}
