package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Customer;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
