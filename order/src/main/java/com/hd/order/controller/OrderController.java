package com.hd.order.controller;

import com.hd.order.common.Body;
import com.hd.order.dto.response.OrderResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@Tag(name = "Order V0.1", description = "Order 관련 API 입니다.")
@RestController
public class OrderController {

    @Operation(summary = "상품조회", description = "상품조회을 진행한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = Body.class))),
            @ApiResponse(responseCode = "400", description = "name 또는 price 입력 오류", content = @Content(schema = @Schema(implementation = Body.class))),
            @ApiResponse(responseCode = "500", description = "name 중복 오류 ", content = @Content(schema = @Schema(implementation = Body.class))),
    })
    @GetMapping("/order")
    public ResponseEntity<?> getOrder(){
        OrderResponseDto order = OrderResponseDto.builder()
                .id(100L)
                .name("Pants")
                .price(10000L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(Body.builder().state(HttpStatus.OK.value()).data(order).build());
    }
}
