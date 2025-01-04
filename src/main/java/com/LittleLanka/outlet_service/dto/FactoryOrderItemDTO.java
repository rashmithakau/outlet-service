package com.LittleLanka.outlet_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactoryOrderItemDTO {
    private int facOrderItemId;
    private int facOrderID;
    private int productID;
    private double quantity;
}
