package com.example.codebase.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Queue entity.
 */
@Entity
@Table(name = "tbl_queue")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(name = "queue_position")
    private Integer queuePosition;

    @Column(name = "estimated_waiting_time")
    private String estimatedWaitingTime;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean served;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean disabled;
}
