package com.lloms.outlet_service.controller;

import com.lloms.outlet_service.dto.request.FactoryOrderRequestDTO;
import com.lloms.outlet_service.service.FactoryOrderService;
import com.lloms.outlet_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fac-order")
@CrossOrigin
@AllArgsConstructor
public class FactoryOrderController {
    private final FactoryOrderService factoryOrderService;
    @PostMapping("")
    public ResponseEntity<StandardResponse> saveFacOrder(@RequestBody FactoryOrderRequestDTO factoryOrderRequestDTO) {
        System.out.println(factoryOrderRequestDTO);
        factoryOrderService.saveFacOrder(factoryOrderRequestDTO);
        return new ResponseEntity<>(new StandardResponse(), HttpStatus.CREATED);
    }
}
