package com.lloms.outlet_service.controller;

import com.lloms.outlet_service.dto.request.CustomerOrderRequestDTO;
import com.lloms.outlet_service.service.CustomerOrderService;
import com.lloms.outlet_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cus-order")
@CrossOrigin
@AllArgsConstructor
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;
    @PostMapping("")
    public ResponseEntity<StandardResponse> saveCusOrder(@RequestBody CustomerOrderRequestDTO customerOrderRequestDTO) {
        System.out.println(customerOrderRequestDTO);
        customerOrderService.saveFacOrder(customerOrderRequestDTO);
        return new ResponseEntity<>(new StandardResponse(), HttpStatus.CREATED);
    }
}
