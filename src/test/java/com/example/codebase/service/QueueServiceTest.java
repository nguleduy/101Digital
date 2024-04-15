package com.example.codebase.service;

import com.example.codebase.dto.QueueDto;
import com.example.codebase.mapper.QueueMapper;
import com.example.codebase.model.Queue;
import com.example.codebase.repository.QueueRepository;
import com.example.codebase.service.impl.QueueServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class QueueServiceTest {
    @Mock
    private QueueRepository queueRepository;

    @InjectMocks
    private QueueServiceImpl queueService;

    public QueueServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void configureQueue_Successful() {
        QueueDto inputDto = new QueueDto(1L);
        Queue queueEntity = QueueMapper.INSTANCE.toEntity(inputDto);
        when(queueRepository.save(any())).thenReturn(queueEntity);

        QueueDto resultDto = queueService.configureQueue(inputDto);

        assertNotNull(resultDto);
        verify(queueRepository, times(1)).save(queueEntity);
    }
}
