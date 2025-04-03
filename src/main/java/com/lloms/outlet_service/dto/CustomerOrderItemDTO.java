package com.lloms.outlet_service.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrderItemDTO {
    private int productId;
    private String productName;
    private double quantity;
    private double unitPrice;
    private double discountPerUnit;
}
