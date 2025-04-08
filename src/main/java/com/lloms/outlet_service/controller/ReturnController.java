package com.lloms.outlet_service.controller;

import com.lloms.outlet_service.dto.OutletReturnDTO;
import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.enums.OutletReturnStatus;
import com.lloms.outlet_service.service.ReturnService;
import com.lloms.outlet_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public ResponseEntity<StandardResponse> updateReturnStatus(@RequestParam Integer returnId, @RequestParam OutletReturnStatus status) {
        returnService.updateReturnStatus(returnId,status);
        StandardResponse response = new StandardResponse();
        response.setCode(HttpStatus.OK.value());
        response.setData("success");
        response.setMessage("successfully updated return status");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<StandardResponse> getAllReturns() {
        List<OutletReturnDTO> resData=returnService.getAllReturns();
        StandardResponse response = new StandardResponse();
        response.setCode(HttpStatus.OK.value());
        response.setData(resData);
        response.setMessage("successfully fetched returns");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-by-status")
    public ResponseEntity<StandardResponse> getAllReturnsByStatus(@RequestParam OutletReturnStatus status) {
        List<OutletReturnDTO> resData=returnService.getAllReturnsByStatus(status);
        StandardResponse response = new StandardResponse();
        response.setCode(HttpStatus.OK.value());
        response.setData(resData);
        response.setMessage("successfully fetched returns");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-by-outletId")
    public ResponseEntity<StandardResponse> getAllReturnsByOutletId(@RequestParam Integer outletId) {
        List<OutletReturnDTO> resData=returnService.getAllReturnsByOutletId(outletId);
        StandardResponse response = new StandardResponse();
        response.setCode(HttpStatus.OK.value());
        response.setData(resData);
        response.setMessage("successfully fetched returns");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-not-pending")
    public ResponseEntity<StandardResponse> getAllReturnsNotPending() {
        List<OutletReturnDTO> resData=returnService.getAllReturnsNotPending();
        StandardResponse response = new StandardResponse();
        response.setCode(HttpStatus.OK.value());
        response.setData(resData);
        response.setMessage("successfully fetched returns");
        return ResponseEntity.ok(response);
    }
}
