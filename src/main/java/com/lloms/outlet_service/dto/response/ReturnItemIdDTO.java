package com.lloms.outlet_service.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnItemIdDTO {
    private Integer returnItemId;
    private double quantity;
}
