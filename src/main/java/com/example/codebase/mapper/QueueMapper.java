package com.example.codebase.mapper;

import com.example.codebase.dto.QueueDto;
import com.example.codebase.dto.UserDto;
import com.example.codebase.model.Queue;
import com.example.codebase.model.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Queue mapper.
 */
@Mapper
public interface QueueMapper {
    QueueMapper INSTANCE = Mappers.getMapper(QueueMapper.class);

    QueueDto toDto(Queue queue);

    List<QueueDto> toListDto(List<Queue> queue);

    Queue toEntity(QueueDto dto);

    UserDto toCustomerDto(User user);
}
