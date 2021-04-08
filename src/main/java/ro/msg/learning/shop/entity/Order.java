package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private String address_country;
    private String address_city;
    private String address_county;
    private String address_street_address;
}