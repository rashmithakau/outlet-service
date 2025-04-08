package com.lloms.outlet_service.dto;

import com.lloms.outlet_service.entity.OutletReturn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReturnItemDto {
    private Integer productId;
    private double quantity;
    private String reason;
    private String productName;
    private Double unitPrice;
}
