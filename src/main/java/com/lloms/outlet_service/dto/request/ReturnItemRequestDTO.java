package com.lloms.outlet_service.dto.request;

import com.lloms.outlet_service.entity.OutletReturn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnItemRequestDTO {
    private Integer returnItemId;
    private double quantity;
    private OutletReturn outletReturn;
}
