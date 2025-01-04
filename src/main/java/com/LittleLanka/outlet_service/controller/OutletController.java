package com.LittleLanka.outlet_service.controller;

import com.LittleLanka.outlet_service.dto.OutletDTO;
import com.LittleLanka.outlet_service.service.OutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/outlet")
public class OutletController {
    @Autowired
    private OutletService outletService;

    @PostMapping("/save-outlet")
    public ResponseEntity<OutletDTO> saveOutlet(@RequestBody OutletDTO outletDTO) {
        OutletDTO outletDTO1=outletService.saveOutlet(outletDTO);
        return ResponseEntity.ok(outletDTO1);
    }


}
