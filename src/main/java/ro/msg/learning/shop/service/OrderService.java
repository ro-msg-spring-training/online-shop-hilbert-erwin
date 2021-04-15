package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderCreateDto;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Order;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.AddressRepository;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.strategy.LocationStrategy;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final LocationStrategy strategy;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final StockRepository stockRepository;

    public List<OrderDto> create(OrderCreateDto orderCreateDto) {

        List<OrderDto> createdOrders = new ArrayList<>();
        List<StockDto> stockDtos = strategy.findStock(orderCreateDto);

        Address address = orderCreateDto.getAddress();
        address = addressRepository.save(address);

        for(StockDto stockDto : stockDtos)
        {
            Order order;
            List<Order> orders = orderRepository.findByCreatedAtAndShippedFromIdAndAddressId(
                    orderCreateDto.getCreatedAt(), stockDto.getLocation().getId(), address.getId());

            if(orders.isEmpty()) {
                order = Order.builder().shippedFrom(stockDto.getLocation()).createdAt(orderCreateDto.getCreatedAt())
                        .address(address).build();
                orderRepository.save(order);
                createdOrders.add(OrderDto.builder().shippedFrom(order.getShippedFrom())
                    .address(order.getAddress())
                    .createdAt(order.getCreatedAt())
                    .customer(order.getCustomer()).build()
                );
            } else {
                order = orders.get(0);
            }

            OrderDetail orderDetail = OrderDetail.builder().order(order).product(stockDto.getProduct()).quantity(stockDto.getQuantity()).build();
            orderDetailRepository.save(orderDetail);

            Stock stock = stockRepository.findByProductIdAndLocationId(stockDto.getProduct().getId(), stockDto.getLocation().getId()).get(0);
            stock.setQuantity(stock.getQuantity() - stockDto.getQuantity());
            stockRepository.save(stock);

        }

        return createdOrders;
    }

}
