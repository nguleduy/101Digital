package com.example.codebase.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User entity.
 */
@Entity
@Table(name = "tbl_user")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(name = "mobile_number", unique = true)
    private String mobileNumber;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String type;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean disabled;

    public User(Long id) {
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
