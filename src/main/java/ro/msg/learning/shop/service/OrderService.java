package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configuration.LocationConfiguration;
import ro.msg.learning.shop.dto.OrderCreateDto;
import ro.msg.learning.shop.entity.Order;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.strategy.LocationStrategy;

@Service
@AllArgsConstructor
public class OrderService {

    private final LocationStrategy strategy;

    public void create(OrderCreateDto orderCreateDto) {
        
        System.out.println(strategy.findStock(orderCreateDto));

    }

}
