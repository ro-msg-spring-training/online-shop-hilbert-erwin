package ro.msg.learning.shop.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orderz")
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "shipped_from")
    private Location shippedFrom;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

}