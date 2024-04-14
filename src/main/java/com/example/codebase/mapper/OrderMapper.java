package com.example.codebase.mapper;

import com.example.codebase.dto.MenuDto;
import com.example.codebase.dto.OrderDto;
import com.example.codebase.dto.UserDto;
import com.example.codebase.model.Menu;
import com.example.codebase.model.Order;
import com.example.codebase.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Order mapper.
 */
@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto toDto(Order order);

    Order toEntity(OrderDto dto);

    UserDto toCustomerDto(User user);

    MenuDto toMenuDto(Menu menu);
}
