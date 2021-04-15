package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Supplier findByName(String name);

}
