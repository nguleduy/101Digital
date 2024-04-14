package com.example.codebase.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Time;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Shop entity.
 */
@Entity
@Table(name = "tbl_shop")
@Data
@NoArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "coffee_options", columnDefinition = "JSONB")
    private String coffeeOptions;

    @Column(name = "queue_settings", columnDefinition = "JSONB")
    private String queueSettings;

    @Column(name = "opening_time")
    private Time openingTime;

    @Column(name = "closing_time")
    private Time closingTime;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean disabled;

    public Shop(Long id) {
        this.id = id;
    }

    /**
     * Constructor.
     */
    public Shop(Long id, Double latitude, Double longitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
