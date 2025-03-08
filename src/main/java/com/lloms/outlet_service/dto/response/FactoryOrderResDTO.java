package com.lloms.outlet_service.dto.response;

import com.lloms.outlet_service.enums.FactoryOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FactoryOrderResDTO {
    private Integer facOrderId;
    private String outletName;
    private Date orderDate;
    private FactoryOrderStatus status;
}
