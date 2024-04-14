package com.example.codebase.mapper;

import com.example.codebase.dto.UserDto;
import com.example.codebase.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Customer mapper.
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    // We ignore id mapping because it's auto-generated
    UserDto toDto(User entity);

    User toEntity(UserDto dto);
}
