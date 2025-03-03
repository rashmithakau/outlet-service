package com.lloms.outlet_service.controller;

import com.lloms.outlet_service.dto.request.OutletSaveRequestDTO;
import com.lloms.outlet_service.service.OutletService;
import com.lloms.outlet_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/outlet")
@CrossOrigin
public class OutletController {
    @Autowired
    private OutletService outletService;

    @PostMapping(
            path = {"/save"}
    )
    public ResponseEntity<StandardResponse> saveOutlet(@RequestBody OutletSaveRequestDTO outletSaveRequestDTO){
       String message = outletService.saveOutlet(outletSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED
        );
    }
}
