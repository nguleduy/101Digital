package com.example.codebase.repository;

import com.example.codebase.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order repository.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
