package com.lloms.outlet_service.dto;


import com.lloms.outlet_service.enums.OutletStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutletDTO {
    private String outletId;
    private String outletName;
    private String location;
    private OutletStatus status;
    private String imageUrl;
}
