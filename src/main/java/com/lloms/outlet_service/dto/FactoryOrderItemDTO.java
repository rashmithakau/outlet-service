package com.lloms.outlet_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactoryOrderItemDTO {
    private int productId;
    private String productName;
    private double quantity;
    private double unitPrice;
}
