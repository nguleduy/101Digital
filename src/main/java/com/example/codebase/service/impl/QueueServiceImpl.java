package com.example.codebase.service.impl;

import com.example.codebase.dto.QueueDto;
import com.example.codebase.mapper.QueueMapper;
import com.example.codebase.model.Queue;
import com.example.codebase.repository.QueueRepository;
import com.example.codebase.service.QueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Queue service impl.
 */
@Service
@Slf4j
public class QueueServiceImpl implements QueueService {
    private final QueueRepository queueRepository;

    public QueueServiceImpl(final QueueRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    @Override
    public QueueDto configureQueue(QueueDto dto) {
        Queue entity = QueueMapper.INSTANCE.toEntity(dto);
        entity = queueRepository.save(entity);
        log.info("Save Queue successful.");
        return QueueMapper.INSTANCE.toDto(entity);
    }
}
