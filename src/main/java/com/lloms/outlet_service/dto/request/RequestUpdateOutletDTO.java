package com.lloms.outlet_service.dto.request;

import com.lloms.outlet_service.enums.OutletStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateOutletDTO {
    private String outletName;
    private String location;
    private OutletStatus status;
}