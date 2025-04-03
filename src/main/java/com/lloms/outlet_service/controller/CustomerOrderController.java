package com.lloms.outlet_service.controller;

import com.lloms.outlet_service.dto.CustomerOrderItemDTO;
import com.lloms.outlet_service.dto.request.CustomerOrderRequestDTO;
import com.lloms.outlet_service.dto.response.CusOrderResponseDTO;
import com.lloms.outlet_service.service.CustomerOrderService;
import com.lloms.outlet_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all-by-outlet/{id}")
    public ResponseEntity<StandardResponse> findAllByOutletId(@PathVariable("id") int outletId) {
        List<CusOrderResponseDTO> allOrders = customerOrderService.findAllByOutletId(outletId);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.OK.value(), "Successfully fetch all cusOrders", allOrders),
                HttpStatus.OK);
    }

    @GetMapping("/items")
    public ResponseEntity<StandardResponse> getCusOrderItems(@RequestParam Integer cusOrId) {//any=>all Confirmed Canceled
        List<CustomerOrderItemDTO> customerOrderItemDTOS= customerOrderService.getCusOrderItems(cusOrId);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.OK.value(), "Successfully fetch all cusOrders", customerOrderItemDTOS),
                HttpStatus.OK);
    }
}
