package com.example.codebase.repository;

import com.example.codebase.model.Queue;
import com.example.codebase.model.Shop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Queue repository.
 */
@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {

    List<Queue> findByShopAndDisabledFalse(Shop shop);

}
