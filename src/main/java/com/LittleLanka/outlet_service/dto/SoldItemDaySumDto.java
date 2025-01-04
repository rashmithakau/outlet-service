package com.LittleLanka.outlet_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldItemDaySumDto {
    private int productId;
    private String productName;
    private Double soldQuantity;
    private String unitName;

}
