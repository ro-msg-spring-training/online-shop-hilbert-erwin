package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Entity
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
    @JoinColumn(name = "product_category")
    private ProductCategory product_category;

    @ManyToOne
    @JoinColumn(name = "supplier")
    private Supplier supplier;

}
