package com.lloms.outlet_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrderItemDTO {
    private String cusOrderItemId;
    private String cusOrderID;
    private String productID;
    private double quantity;
}
