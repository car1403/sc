package com.hd.order.dto.response;


import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    Long id;
    String name;
    Long price;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;


}
