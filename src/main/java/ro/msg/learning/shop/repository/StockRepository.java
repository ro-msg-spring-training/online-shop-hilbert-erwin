package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
