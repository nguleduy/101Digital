package com.example.codebase.dto;

import java.time.Duration;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Queue dto.
 */
@Data
@NoArgsConstructor
public class QueueDto {
    private Long id;
    private ShopDto shop;
    private OrderDto order;
    private int queuePosition;
    private Duration estimatedWaitingTime;
    private Boolean served;
    private Boolean disabled;

    public QueueDto(Long id) {
        this.id = id;
    }
}
