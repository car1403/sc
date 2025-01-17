package com.hd.order.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Item Response DTO")
public class OrderRequestDto {

    Long id;
    @Schema(description = "Item Name", required = true, type = "String", example = "pants1")
    @NotEmpty(message = "Name cannot be empty")
    String name;
    @Schema(description = "Item Price", required = true, type = "int", example = "20000")
    @Min(value = 10, message = "10이상이어야 함")
    Long price;



}
