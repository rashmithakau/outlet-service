package com.LittleLanka.outlet_service.dto;

import com.LittleLanka.outlet_service.dto.enums.FactoryOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FactoryOrderDTO {
    private int OrderID;
    private int OutletID;
    private Date OrderDate;
    private LocalTime OrderTime;
    private FactoryOrderStatus Status;
}
