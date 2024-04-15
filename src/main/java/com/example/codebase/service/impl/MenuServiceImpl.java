package com.example.codebase.service.impl;

import com.example.codebase.dto.MenuDto;
import com.example.codebase.mapper.MenuMapper;
import com.example.codebase.model.Menu;
import com.example.codebase.repository.MenuRepository;
import com.example.codebase.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Menu service impl.
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    public MenuServiceImpl(final MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public MenuDto manageMenu(MenuDto dto) {
        Menu menu = MenuMapper.INSTANCE.toEntity(dto);
        menu = menuRepository.save(menu);
        log.info("Save Menu successful.");
        return MenuMapper.INSTANCE.toDto(menu);
    }
}
