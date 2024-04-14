package com.example.codebase.mapper;

import com.example.codebase.dto.PromotionDto;
import com.example.codebase.dto.UserDto;
import com.example.codebase.model.Promotion;
import com.example.codebase.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Promotion mapper.
 */
@Mapper
public interface PromotionMapper {

    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

    PromotionDto toDto(Promotion promotion);

    Promotion toEntity(PromotionDto dto);

    UserDto toCustomerdto(User user);
}
