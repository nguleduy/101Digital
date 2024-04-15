package com.example.codebase.service.impl;

import com.example.codebase.dto.ShopDto;
import com.example.codebase.mapper.ShopMapper;
import com.example.codebase.model.Shop;
import com.example.codebase.repository.ShopRepository;
import com.example.codebase.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Shop service impl.
 */
@Service
@Slf4j
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    public ShopServiceImpl(final ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public ShopDto setupShop(ShopDto shop) {
        Shop entity = ShopMapper.INSTANCE.toEntity(shop);
        entity = shopRepository.save(entity);
        log.info("Save Shop successful.");
        return ShopMapper.INSTANCE.toDto(entity);
    }
}
