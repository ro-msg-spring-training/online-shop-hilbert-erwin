package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.Entity;

@Data
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

    private String address_country;
    private String address_city;
    private String address_county;
    private String address_street_address;
}
