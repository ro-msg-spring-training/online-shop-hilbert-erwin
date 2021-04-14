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
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SingleLocationTest {

    @Mock
    private StockRepository stockRepository;
    @Mock
    private LocationRepository locationRepository;
    @InjectMocks
    private SingleLocationStrategy strategy;

    private Location location = Location.builder().name("location1").build();
    private Product product = Product.builder().name("product1").build();
    private Stock stock;
    private List<Stock> stocks;
    private List<Location> locations = new ArrayList<>();
    private List<OrderDetailDto> orderDetailDtos;
    private OrderCreateDto orderCreateDto;

    @BeforeEach
    public void init() {
        location.setId(1L);
        product.setId(1L);
        locations.add(location);
        stock = Stock.builder().product(product).quantity(5).location(location).build();
        stocks = new ArrayList<>();
        stocks.add(stock);
        Mockito.when(locationRepository.findAll()).thenReturn(locations);
    }

    @Test
    void findStock_sufficientStock_stockDtoNotEmpty() {
        Mockito.when(stockRepository.findByProductIdAndQuantityGreaterThanEqualAndLocationId(product.getId(),1,location.getId())).thenReturn(stocks);
        orderDetailDtos = new ArrayList<>();
        orderDetailDtos.add(OrderDetailDto.builder().productId(product.getId()).quantity(1).build());
        orderCreateDto = OrderCreateDto.builder().orderDetailDtos(orderDetailDtos).build();
        assertFalse(strategy.findStock(orderCreateDto).isEmpty());
    }


    @Test
    void findStock_noStock_exceptionThrown() {
        Mockito.when(stockRepository.findByProductIdAndQuantityGreaterThanEqualAndLocationId(product.getId(),1,location.getId())).thenReturn(new ArrayList<>());
        orderDetailDtos = new ArrayList<>();
        orderDetailDtos.add(OrderDetailDto.builder().productId(product.getId()).quantity(1).build());
        orderCreateDto = OrderCreateDto.builder().orderDetailDtos(orderDetailDtos).build();
        assertThrows(RuntimeException.class, () -> strategy.findStock(orderCreateDto));
    }


}
