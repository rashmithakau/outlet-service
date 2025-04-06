package com.lloms.outlet_service.controller;

import com.lloms.outlet_service.dto.OutletReturnDTO;
import com.lloms.outlet_service.dto.response.CusOrderResponseDTO;
import com.lloms.outlet_service.dto.response.ReturnItemIdDTO;
import com.lloms.outlet_service.service.OutletReturnService;
import com.lloms.outlet_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin
public class OutletReturnController {

    @Autowired
    private OutletReturnService outletReturnService;

    @PostMapping("save/outlet-return")
    public ResponseEntity<StandardResponse> saveOutletReturn(@RequestBody OutletReturnDTO outletReturnDTO) {
        System.out.println(outletReturnDTO);
        outletReturnService.saveOutletReturn(outletReturnDTO);
        return new ResponseEntity<>(new StandardResponse(),HttpStatus.CREATED);
    }

    @GetMapping("/get/returnItemIds")
    public ResponseEntity<StandardResponse> findAllItemsId(@RequestBody Integer returnId) {
        List<ReturnItemIdDTO> returnItemIdDTOS = outletReturnService.findAllItemsId(returnId);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.OK.value(), "Successfully fetch all return items", returnItemIdDTOS),
                HttpStatus.OK);
    }

}
