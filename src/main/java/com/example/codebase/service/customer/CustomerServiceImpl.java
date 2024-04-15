package com.example.codebase.service.customer;

import com.example.codebase.dto.OrderDto;
import com.example.codebase.dto.QueueDto;
import com.example.codebase.dto.ShopDto;
import com.example.codebase.exception.CustomException;
import com.example.codebase.mapper.OrderMapper;
import com.example.codebase.mapper.QueueMapper;
import com.example.codebase.mapper.ShopMapper;
import com.example.codebase.model.Order;
import com.example.codebase.model.Queue;
import com.example.codebase.model.Shop;
import com.example.codebase.repository.OrderRepository;
import com.example.codebase.repository.QueueRepository;
import com.example.codebase.repository.ShopRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Customer service impl.
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final ShopRepository shopRepository;
    private final QueueRepository queueRepository;
    private final OrderRepository orderRepository;

    /**
     * Constructor.
     */
    public CustomerServiceImpl(final ShopRepository shopRepository,
                               final QueueRepository queueRepository,
                               final OrderRepository orderRepository) {
        this.shopRepository = shopRepository;
        this.queueRepository = queueRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<ShopDto> findNearestShops(double latitude, double longitude) {
        List<Shop> shops = shopRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(
                        shop -> calculateDistance(
                                latitude,
                                longitude,
                                shop.getLatitude(),
                                shop.getLongitude())))
                .collect(Collectors.toList());
        if (shops.isEmpty()) {
            log.error("There is no shops near by you.");
            throw new CustomException(HttpStatus.NOT_FOUND.value(),
                    "There is no shops near by you.");
        }
        log.info("Fetch nearest shops successful. {}", shops);
        return ShopMapper.INSTANCE.toListDto(shops);
    }

    /**
     * Using Haversine formula.
     */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double radius = 6371; // Radius of the earth in km
        double latitude = Math.toRadians(lat2 - lat1);
        double longitude = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latitude / 2) * Math.sin(latitude / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(longitude / 2) * Math.sin(longitude / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radius * c; // Distance in km
    }

    @Override
    @Transactional
    public QueueDto placeOrder(OrderDto dto) {
        Order order = orderRepository.save(OrderMapper.INSTANCE.toEntity(dto));
        log.info("Save Order successful. {}", order);
        Queue queue = queueRepository.save(
                Queue.builder()
                        .shop(order.getShop())
                        .order(order).build());
        log.info("Save Queue successful. {}", queue);
        return QueueMapper.INSTANCE.toDto(queue);
    }

    @Override
    public List<QueueDto> viewQueueByShop(Long shopId) {
        return QueueMapper.INSTANCE.toListDto(
                queueRepository.findByShopAndDisabledFalse(new Shop(shopId)));
    }

    @Override
    public QueueDto cancelOrder(OrderDto dto) {
        Optional<Queue> queue = queueRepository.findAll().stream()
                .filter(i -> Objects.equals(i.getOrder().getId(), dto.getId())
                        && Objects.equals(i.getShop().getId(), dto.getShop().getId())).findFirst();
        if (queue.isPresent()) {
            queue.get().setDisabled(true);
            Queue result = queue.get();
            queueRepository.save(result);
            log.info("Save Queue successful.");
            return QueueMapper.INSTANCE.toDto(result);
        }

        log.error("Order not found");
        throw new CustomException(HttpStatus.NOT_FOUND.value(), "Order not found");
    }
}
