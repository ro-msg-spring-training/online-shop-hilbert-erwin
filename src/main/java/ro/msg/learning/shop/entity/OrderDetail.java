package ro.msg.learning.shop.entity;


import lombok.*;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "orderz")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    private Integer quantity;

}
