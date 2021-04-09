package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.Entity;

@Data
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BaseEntity {

    private String name;
    private String description;

}
