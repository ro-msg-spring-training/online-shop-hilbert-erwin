package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.dto.OrderCreateDto;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class AbundantLocationStrategy implements LocationStrategy{

    private StockRepository stockRepository;

    @Override
    public List<StockDto> findStock(OrderCreateDto orderCreateDto) {
        List<StockDto> stockDtos = new ArrayList<>();
        List<Stock> stocks = null;
        for(OrderDetailDto orderDetailDto : orderCreateDto.getOrderDetailDtos()) {
            stocks = stockRepository.findByProductIdAndQuantityGreaterThanEqualOrderByQuantityDesc(orderDetailDto.getProductId(), orderDetailDto.getQuantity());
            if(stocks.size() != 0) {
                Stock stock = stocks.get(0);
                stockDtos.add(new StockDto(stock.getLocation(), stock.getProduct(), orderDetailDto.getQuantity()));
            } else {
                throw new RuntimeException();
            }
        }
        return stockDtos;
    }
}
