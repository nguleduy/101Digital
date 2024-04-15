package com.example.codebase.service;

import com.example.codebase.dto.ShopDto;
import com.example.codebase.mapper.ShopMapper;
import com.example.codebase.model.Shop;
import com.example.codebase.repository.ShopRepository;
import com.example.codebase.service.impl.ShopServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShopServiceTest {
    @Mock
    private ShopRepository shopRepository;

    @InjectMocks
    private ShopServiceImpl shopService;

    public ShopServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void setupShop_Successful() {
        ShopDto inputDto = new ShopDto(1L);
        Shop shopEntity = ShopMapper.INSTANCE.toEntity(inputDto);
        when(shopRepository.save(any())).thenReturn(shopEntity);

        ShopDto resultDto = shopService.setupShop(inputDto);

        assertNotNull(resultDto);
        verify(shopRepository, times(1)).save(shopEntity);
    }
}
