package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    private Location location;
    private Product product;
    private Integer quantity;

}
