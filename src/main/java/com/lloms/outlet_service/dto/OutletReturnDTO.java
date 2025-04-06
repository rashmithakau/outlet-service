package com.lloms.outlet_service.dto;

import com.lloms.outlet_service.dto.response.ReturnItemIdDTO;
import com.lloms.outlet_service.entity.ReturnItem;
import com.lloms.outlet_service.enums.OutletReturnStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutletReturnDTO {
    private int returnId;
    private Date returnDate;
    private Time returnTime;
    private String outletName;
    private OutletReturnStatus outletReturnStatus;
    private List<ReturnItemIdDTO> returnItems;
}
