package ro.msg.learning.shop.integration;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.dto.OrderCreateDto;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.AddressRepository;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.OrderService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ShopApplication.class)
@ActiveProfiles("test")
@Sql(scripts="classpath:delete.sql",executionPhase=Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts="classpath:delete.sql",executionPhase=Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class OrderTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private AddressRepository addressRepository;


    protected Product product1 = Product.builder().name("product150").build();
    protected Product product2 = Product.builder().name("product2").build();
    protected Location location1 = Location.builder().name("location1").build();
    protected Location location2 = Location.builder().name("location2").build();
    protected Stock stock1 = Stock.builder().location(location1).product(product1).quantity(2).build();
    protected Stock stock2 = Stock.builder().location(location2).product(product1).quantity(5).build();
    protected Stock stock3 = Stock.builder().location(location1).product(product2).quantity(2).build();
    protected Address address = Address.builder().address_country("Romania").build();


    @Before
    public void init() {
        productRepository.save(product1);
        productRepository.save(product2);
        locationRepository.save(location1);
        locationRepository.save(location2);
        stockRepository.save(stock1);
        stockRepository.save(stock2);
        stockRepository.save(stock3);
        addressRepository.save(address);
    }

    @Test
    public void createOrder_enoughStock_returnsOrder() {
        OrderCreateDto orderCreateDto = new OrderCreateDto();
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        orderDetailDtos.add(OrderDetailDto.builder().productId(product1.getId()).quantity(1).build());
        orderDetailDtos.add(OrderDetailDto.builder().productId(product2.getId()).quantity(1).build());
        orderCreateDto.setOrderDetailDtos(orderDetailDtos);
        orderCreateDto.setCreatedAt(LocalDateTime.now());
        orderCreateDto.setAddress(address);
        assertFalse(orderService.create(orderCreateDto).isEmpty());
    }

    @Test
    public void createOrder_noStock_throwsException() {
        OrderCreateDto orderCreateDto = new OrderCreateDto();
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        orderDetailDtos.add(OrderDetailDto.builder().productId(product1.getId()).quantity(100).build());
        orderDetailDtos.add(OrderDetailDto.builder().productId(product2.getId()).quantity(1).build());
        orderCreateDto.setOrderDetailDtos(orderDetailDtos);
        orderCreateDto.setCreatedAt(LocalDateTime.now());
        orderCreateDto.setAddress(address);
        assertThrows(RuntimeException.class, () -> orderService.create(orderCreateDto));
    }

}
