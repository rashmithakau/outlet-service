package com.lloms.outlet_service.dto.intercommunication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseGetAllProductsDTO {
    private Long productId;
    private String productName;
    private String productCatagory;
    private String productMeasuringUnitType;
    private String imageUrl;
    private Double price;  // Should handle null values correctly
    private boolean productStatus;
}
