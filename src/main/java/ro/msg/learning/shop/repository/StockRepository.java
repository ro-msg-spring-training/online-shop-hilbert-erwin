package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Stock;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByProductIdAndQuantityGreaterThanEqualOrderByQuantityDesc(Long id, Integer quantity);
    List<Stock> findByProductIdAndQuantityGreaterThanEqualAndLocationId(Long productId, Integer quantity, Long locationId);
    List<Stock> findByProductIdAndLocationId(Long productId, Long locationId);

}
