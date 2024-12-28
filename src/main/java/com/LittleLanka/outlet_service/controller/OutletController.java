package com.LittleLanka.outlet_service.controller;

import com.LittleLanka.outlet_service.dto.OutletDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/outlet")
public class OutletController {

    @PostMapping("/save-outlet")
    public ResponseEntity<OutletDTO> saveOutlet(@RequestBody OutletDTO outletDTO) {
        OutletDTO
    }
}
