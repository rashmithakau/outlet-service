package com.lloms.outlet_service.controller;

import com.lloms.outlet_service.dto.request.OutletSaveRequestDTO;
import com.lloms.outlet_service.dto.request.RequestUpdateOutletDTO;
import com.lloms.outlet_service.dto.response.ResponseGetOutletDTO;
import com.lloms.outlet_service.service.OutletService;
import com.lloms.outlet_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(
            path = {"/get-outlet-by-id"},
            params = "outlet-id"
    )
    public ResponseEntity<StandardResponse> getOutletById(@RequestParam(value = "outlet-id") int outletId){
        ResponseGetOutletDTO responseGetOutletDTO = outletService.getOutletById(outletId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Outlet is fetched successfully!",responseGetOutletDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            path = {"/get-all-outlets"}
    )
    public ResponseEntity<StandardResponse> getAllOutlets() {
        List<ResponseGetOutletDTO> allOutlets = outletService.getAllOutlets();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "fetch all outlets successfully!", allOutlets),
                HttpStatus.OK
        );
    }

    @PutMapping(
            path = {"/update-outlet"},
            params = "outlet-id"
    )
    public ResponseEntity<StandardResponse> updateOutlet(@RequestBody RequestUpdateOutletDTO requestUpdateOutletDTO,
                                                         @RequestParam(value = "outlet-id") int outletID){
        ResponseGetOutletDTO updatedOutlet = outletService.updateOutlet(requestUpdateOutletDTO,outletID);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "outlet is updated successfully!", updatedOutlet),
                HttpStatus.CREATED
        );
    }
}
