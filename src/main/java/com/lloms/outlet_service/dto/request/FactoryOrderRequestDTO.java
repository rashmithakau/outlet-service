package com.lloms.outlet_service.dto.request;

import com.lloms.outlet_service.dto.FactoryOrderItemDTO;
import com.lloms.outlet_service.enums.FactoryOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactoryOrderRequestDTO {
    private Date orderDate;
   // private Time orderTime;
    private FactoryOrderStatus status;
    private Integer outletId;
    private List<FactoryOrderItemDTO> items;  // List of items in the order
}
