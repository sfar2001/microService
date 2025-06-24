package com.mircoservice.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDto {
    private Long id;
    private String skuCode;
    private BigDecimal price; // Changed to String to handle BigDecimal serialization
    private Integer quantity;

}
