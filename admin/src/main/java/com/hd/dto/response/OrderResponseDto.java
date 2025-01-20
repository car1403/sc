package com.hd.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OrderResponseDto {
    Long id;
    String name;
    Long price;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;


}
