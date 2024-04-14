package com.example.codebase.dto;

import java.sql.Time;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Shop configuration dto.
 */
@Data
@NoArgsConstructor
public class ShopDto {
    private Long id;
    private Double latitude;
    private Double longitude;
    private String address;
    private String phoneNumber;
    private String coffeeOptions;
    private String queueSettings;
    private Time openingTime;
    private Time closingTime;
    private String dayOfWeek;
    private Boolean disabled;

    public ShopDto(Long id) {
        this.id = id;
    }

    /**
     * Constructor.
     */
    public ShopDto(Long id, Double latitude, Double longitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
