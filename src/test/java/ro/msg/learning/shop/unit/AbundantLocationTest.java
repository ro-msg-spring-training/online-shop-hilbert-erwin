package ro.msg.learning.shop.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ro.msg.learning.shop.dto.OrderCreateDto;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.strategy.AbundantLocationStrategy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AbundantLocationTest {

    @Mock
    private StockRepository stockRepository;
    @InjectMocks
    private AbundantLocationStrategy strategy;

    private Location location1 = Location.builder().name("location1").build();
    private Location location2 = Location.builder().name("location2").build();
    private Product product = Product.builder().name("product1").build();
    private Stock stock1;
    private Stock stock2;
    private List<Stock> stocks;
    private List<OrderDetailDto> orderDetailDtos;
    private OrderCreateDto orderCreateDto;

    @BeforeEach
    public void init() {
        location1.setId(1L);
        location2.setId(2L);
        product.setId(1L);
        stock1 = Stock.builder().product(product).quantity(5).location(location1).build();
        stock2 = Stock.builder().product(product).quantity(10).location(location2).build();
        stocks = new ArrayList<>();
        stocks.add(stock2);
        stocks.add(stock1);
    }

    @Test
    void findStock_sufficientStock_stockDtoNotEmpty() {
        Mockito.when(stockRepository.findByProductIdAndQuantityGreaterThanEqualOrderByQuantityDesc(product.getId(),1)).thenReturn(stocks);
        orderDetailDtos = new ArrayList<>();
        orderDetailDtos.add(OrderDetailDto.builder().productId(product.getId()).quantity(1).build());
        orderCreateDto = OrderCreateDto.builder().orderDetailDtos(orderDetailDtos).build();
        assertEquals(location2.getId(),strategy.findStock(orderCreateDto).get(0).getLocation().getId());
    }


    @Test
    void findStock_noStock_exceptionThrown() {
        Mockito.when(stockRepository.findByProductIdAndQuantityGreaterThanEqualOrderByQuantityDesc(product.getId(),1)).thenReturn(new ArrayList<>());
        orderDetailDtos = new ArrayList<>();
        orderDetailDtos.add(OrderDetailDto.builder().productId(product.getId()).quantity(1).build());
        orderCreateDto = OrderCreateDto.builder().orderDetailDtos(orderDetailDtos).build();
        assertThrows(RuntimeException.class, () -> strategy.findStock(orderCreateDto));
    }


}
