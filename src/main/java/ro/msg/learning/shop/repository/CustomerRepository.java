package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUsername(String username);

}
