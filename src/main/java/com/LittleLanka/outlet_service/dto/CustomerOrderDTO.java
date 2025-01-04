package com.LittleLanka.outlet_service.dto;

import com.LittleLanka.outlet_service.dto.enums.CustomerOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrderDTO {
    private int cusOrderID;
    private int outletID;
    private Date orderDate;
    private LocalTime orderTime;
    private CustomerOrderStatus status;
}
