package com.lloms.outlet_service.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrderItemDTO {
    private int cusOrderItemId;
    private int cusOrderID;
    private int productId;
    private double quantity;
    private double discountPerUnit;
}
