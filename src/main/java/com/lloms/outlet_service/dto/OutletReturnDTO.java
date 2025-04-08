package com.lloms.outlet_service.dto;

import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.entity.ReturnItem;
import com.lloms.outlet_service.enums.OutletReturnStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OutletReturnDTO {
    private Integer returnId;
    private LocalDateTime returnDate;
    private Integer outletId;
    private OutletReturnStatus outletReturnStatus;
    private List<ReturnItemDto> returnItems;
}

