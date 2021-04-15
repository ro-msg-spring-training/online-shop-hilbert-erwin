package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class UserDetailsService {

    private CustomerRepository customerRepository;

    public Customer getUser(String username) {
        return customerRepository.findByUsername(username);
    }



}
