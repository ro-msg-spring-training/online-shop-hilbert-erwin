package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrderCreateDto;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Order;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public List<Order> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        orderCreateDto.setCreatedAt(LocalDateTime.now());
        return orderService.create(orderCreateDto);
    }

}
