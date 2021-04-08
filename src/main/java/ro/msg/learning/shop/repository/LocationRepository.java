package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
