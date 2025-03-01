package com.lloms.outlet_service.dto;

import com.lloms.outlet_service.enums.CustomerOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrderDTO {
    private String cusOrderID;
    private String outletID;
    private Date orderDate;
    private double discountPerUnit;
    private LocalTime orderTime;
    private CustomerOrderStatus status;
}
