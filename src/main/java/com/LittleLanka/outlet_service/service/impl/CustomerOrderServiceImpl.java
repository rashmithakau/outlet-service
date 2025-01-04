package com.LittleLanka.outlet_service.service.impl;

import com.LittleLanka.outlet_service.dto.SoldItemDaySumDto;
import com.LittleLanka.outlet_service.repository.CustomerOrderRepository;
import com.LittleLanka.outlet_service.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.List;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    public List<SoldItemDaySumDto> getSoldItemsSumm(DateFormat date, String outletName) {

        return null;
    }
}
