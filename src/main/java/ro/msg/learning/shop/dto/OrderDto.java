package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Location;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Location shippedFrom;
    private Customer customer;
    private LocalDateTime createdAt;
    private Address address;
}
