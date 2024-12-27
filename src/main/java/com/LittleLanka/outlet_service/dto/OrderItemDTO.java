package com.LittleLanka.outlet_service.dto;

import com.LittleLanka.outlet_service.dto.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {
    private int OrderItemID;
    private int OrderID;
    private int ProductID;
    private double Quantity;
    private OrderType OrderType;
}
