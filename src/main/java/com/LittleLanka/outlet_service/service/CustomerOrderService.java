package com.LittleLanka.outlet_service.service;

import com.LittleLanka.outlet_service.dto.SoldItemDaySumDto;

import java.text.DateFormat;
import java.util.List;

public interface CustomerOrderService {
    List<SoldItemDaySumDto> getSoldItemsSumm(DateFormat date, String outletName);
}
