package com.example.codebase.repository;

import com.example.codebase.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Shop repository.
 */
@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
