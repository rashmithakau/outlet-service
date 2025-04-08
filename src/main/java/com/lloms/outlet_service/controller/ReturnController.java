package com.lloms.outlet_service.controller;

import com.lloms.outlet_service.dto.OutletReturnDTO;
import com.lloms.outlet_service.service.ReturnService;
import com.lloms.outlet_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/return")
@CrossOrigin
@AllArgsConstructor
public class ReturnController {
    private final ReturnService returnService;

    @PostMapping
    public ResponseEntity<StandardResponse> saveReturn(@RequestBody OutletReturnDTO returnDTO) {
        returnService.saveReturn(returnDTO);
        StandardResponse response = new StandardResponse();
        response.setCode(HttpStatus.OK.value());
        response.setData("success");
        response.setMessage("successfully saved return");
        return ResponseEntity.ok(response);
    }
}
