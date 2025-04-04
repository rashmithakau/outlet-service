package com.lloms.outlet_service.dto.response;

import com.lloms.outlet_service.enums.CustomerOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CusOrderResponseDTO {
    private Integer cusOrderID;
    private String outletID;
    private String outletName;
    private Date orderDate;
    private CustomerOrderStatus status;
    private String customerName;
    private String customerPhone;
}
