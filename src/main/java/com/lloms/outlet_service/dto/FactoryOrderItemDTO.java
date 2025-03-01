package com.lloms.outlet_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactoryOrderItemDTO {
    private String facOrderItemId;
    private String facOrderID;
    private String productID;
    private double quantity;
}
