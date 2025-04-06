package com.lloms.outlet_service.dto;

import com.lloms.outlet_service.dto.response.ReturnItemIdDTO;
import com.lloms.outlet_service.enums.OutletReturnStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutletReturnDTO {
    private int returnId;
    private int outletID;
    private OutletReturnStatus outletReturnStatus;
    private List<ReturnItemIdDTO> returnItems;
    private LocalDateTime returnTime;
}
