package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.dto.OrderCreateDto;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class SingleLocationStrategy implements  LocationStrategy{

    private  StockRepository stockRepository;
    private  LocationRepository locationRepository;

    @Override
    public List<StockDto> findStock(OrderCreateDto orderCreateDto) {
        List<StockDto> stockDtos = null;
        List<Stock> stocks;
        Stock stock;
        List<Location> locations = locationRepository.findAll();
        for(Location location : locations) {
            Boolean productNotFound = false;
            for(OrderDetailDto orderDetailDto : orderCreateDto.getOrderDetailDtos()) {
                stockDtos = new ArrayList<>();
                if(orderDetailDto.getProductId() == null || orderDetailDto.getQuantity() == null) {
                    throw new RuntimeException();
                }
                stocks = stockRepository.findByProductIdAndQuantityGreaterThanEqualAndLocationId(orderDetailDto.getProductId(),
                        orderDetailDto.getQuantity(), location.getId());
                if(stocks.size() == 0) {
                    productNotFound = true;
                } else {
                    stock = stocks.get(0);
                    stockDtos.add(new StockDto(stock.getLocation(), stock.getProduct(), orderDetailDto.getQuantity()));
                }
            }
            if(!productNotFound) {
                return stockDtos;
            }
        }
        throw new RuntimeException();
    }


}
