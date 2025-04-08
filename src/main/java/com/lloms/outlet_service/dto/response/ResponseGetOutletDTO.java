package com.lloms.outlet_service.dto.response;

import com.lloms.outlet_service.enums.OutletStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseGetOutletDTO {
    private String outletId;
    private String outletName;
    private String location;
    private int userId;
    private OutletStatus status;
    private String imageUrl;
}


