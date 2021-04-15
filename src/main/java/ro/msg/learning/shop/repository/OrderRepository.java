package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Order;
import ro.msg.learning.shop.entity.Stock;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCreatedAtAndShippedFromIdAndAddressId(LocalDateTime createdAt, Long shippedFromId, Long addressId);

}
