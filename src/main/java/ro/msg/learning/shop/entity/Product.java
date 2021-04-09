package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "category")
    private ProductCategory product_category;

    @ManyToOne
    @JoinColumn(name = "supplier")
    private Supplier supplier;

}
