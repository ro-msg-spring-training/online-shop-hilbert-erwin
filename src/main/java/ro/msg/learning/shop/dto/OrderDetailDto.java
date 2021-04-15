package ro.msg.learning.shop.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {

    private Long productId;
    private Integer quantity;

}
