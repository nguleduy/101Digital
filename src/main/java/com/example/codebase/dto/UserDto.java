package com.example.codebase.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer dto.
 */
@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String mobileNumber;
    private String username;
    private String password;
    private String name;
    private String address;
    private Boolean disabled;
    private String type;

    public UserDto(Long id) {
        this.id = id;
    }
}
