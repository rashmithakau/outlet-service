package com.lloms.outlet_service.dto.request;

import com.lloms.outlet_service.dto.CustomerOrderItemDTO;
import com.lloms.outlet_service.enums.CustomerOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderRequestDTO {
    private Date orderDate;
    private CustomerOrderStatus status;
    private Integer outletId;
    private String customerName;
    private String customerPhone;
    private List<CustomerOrderItemDTO> items;
}
