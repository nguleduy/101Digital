package com.example.codebase.mapper;

import com.example.codebase.dto.ShopDto;
import com.example.codebase.model.Shop;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Shop mapper.
 */
@Mapper
public interface ShopMapper {
    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

    ShopDto toDto(Shop shop);

    List<ShopDto> toListDto(List<Shop> dto);

    Shop toEntity(ShopDto dto);
}
