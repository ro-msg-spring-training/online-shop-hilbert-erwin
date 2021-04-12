package ro.msg.learning.shop.dto;

import lombok.*;
import ro.msg.learning.shop.entity.Address;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {

    private LocalDateTime createdAt;
    private Address address;
    private List<OrderDetailDto> orderDetailDtos;



}
