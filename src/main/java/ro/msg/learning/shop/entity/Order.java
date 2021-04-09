package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "location")
    private Location shippedFrom;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

}