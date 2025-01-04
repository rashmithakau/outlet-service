package com.LittleLanka.outlet_service.controller;

import com.LittleLanka.outlet_service.dto.OutletDTO;
import com.LittleLanka.outlet_service.dto.SoldItemDaySumDto;
import com.LittleLanka.outlet_service.service.CustomerOrderService;
import com.LittleLanka.outlet_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/stock")
public class CustomerOrderController {
    @Autowired
    private CustomerOrderService customerOrderService;

    @GetMapping("sold-items-summ/{outletName}/{date}")
    public ResponseEntity<StandardResponse> getSoldItemsSumm(
            @PathVariable("date") DateFormat date,
            @PathVariable("outletName") String outletName)
    {
        List<SoldItemDaySumDto> soldItemDaySumDtos=customerOrderService.getSoldItemsSumm(date,outletName);
        return null;
    }
}
