package com.LittleLanka.outlet_service.dto.response;

import com.LittleLanka.outlet_service.dto.SoldItemDaySumDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseOutletSoldItemDayListDto {
    private Date date;
    private String outletName;
    private List<SoldItemDaySumDto> soldItems;
    private Double dayTotalSale;
}
