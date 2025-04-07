package com.lloms.outlet_service.dto.request;

import com.lloms.outlet_service.enums.OutletStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestUpdateOutletDTO {
    private int userId;
    private String phoneNumber;
    private OutletStatus status;
    private String imageUrl;
}
