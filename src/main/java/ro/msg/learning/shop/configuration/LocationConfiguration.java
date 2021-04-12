package ro.msg.learning.shop.configuration;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.strategy.AbundantLocationStrategy;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.StockRepository;

@Configuration
@AllArgsConstructor
public class LocationConfiguration {

    private final StockRepository stockRepository;
    private final LocationRepository locationRepository;

    @Bean
    public LocationStrategy chooseStrategy(@Value("${strategy}") String strategy) {
        switch (strategy) {
            case "single":
                return new SingleLocationStrategy(stockRepository, locationRepository);
            case "mostAbundant":
                return new AbundantLocationStrategy(stockRepository);
            default:
                throw new RuntimeException();
        }
    }

}
