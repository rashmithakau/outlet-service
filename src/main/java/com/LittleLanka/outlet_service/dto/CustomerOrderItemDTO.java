package com.LittleLanka.outlet_service.dto;

import com.LittleLanka.outlet_service.dto.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrderItemDTO {
    private int cusOrderItemId;
    private int cusOrderID;
    private int productID;
    private double quantity;
}
