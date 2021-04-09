package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.entity.Supplier;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String imageUrl;
    private String productCategoryName;
    private String productCategoryDescription;
    private String supplierName;

    public ProductDto(Product product, ProductCategory productCategory, Supplier supplier) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.weight = product.getWeight();
        this.imageUrl = product.getImage_url();
        this.productCategoryName = productCategory.getName();
        this.productCategoryDescription = productCategory.getDescription();
        this.supplierName = supplier.getName();
    }

}
