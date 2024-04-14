package com.example.codebase.repository;

import com.example.codebase.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Menu repository.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
