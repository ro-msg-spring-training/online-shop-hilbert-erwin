package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrderCreateDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public List<OrderDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        orderCreateDto.setCreatedAt(LocalDateTime.now());
        return orderService.create(orderCreateDto);
    }

}
