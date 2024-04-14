package com.example.codebase.mapper;

import com.example.codebase.dto.MenuDto;
import com.example.codebase.model.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Menu mapper.
 */
@Mapper
public interface MenuMapper {
    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    @Mapping(target = "id", ignore = true)
    MenuDto toDto(Menu menu);

    Menu toEntity(MenuDto dto);
}
