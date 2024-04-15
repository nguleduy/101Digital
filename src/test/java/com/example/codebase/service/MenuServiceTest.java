package com.example.codebase.service;

import com.example.codebase.dto.MenuDto;
import com.example.codebase.mapper.MenuMapper;
import com.example.codebase.model.Menu;
import com.example.codebase.repository.MenuRepository;
import com.example.codebase.service.impl.MenuServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MenuServiceTest {
    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuServiceImpl menuService;
    public MenuServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void manageMenu_Successful() {
        MenuDto inputDto = new MenuDto(1L);
        Menu menuEntity = MenuMapper.INSTANCE.toEntity(inputDto);
        when(menuRepository.save(any())).thenReturn(menuEntity);

        MenuDto resultDto = menuService.manageMenu(inputDto);

        assertNotNull(resultDto);
        verify(menuRepository, times(1)).save(menuEntity);
    }
}
