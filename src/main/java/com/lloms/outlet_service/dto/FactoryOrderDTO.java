package com.lloms.outlet_service.dto;


import com.lloms.outlet_service.enums.FactoryOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FactoryOrderDTO {
    private String orderId;
    private String outletId;
    private Date orderDate;
    private LocalTime orderTime;
    private FactoryOrderStatus status;
}
