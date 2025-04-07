package com.lloms.outlet_service.controller;

import com.lloms.outlet_service.dto.OutletDTO;
import com.lloms.outlet_service.dto.request.OutletSaveRequestDTO;
import com.lloms.outlet_service.dto.request.RequestUpdateOutletDTO;
import com.lloms.outlet_service.dto.response.ResponseGetOutletDTO;
import com.lloms.outlet_service.service.OutletService;
import com.lloms.outlet_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/outlet")
@CrossOrigin
@AllArgsConstructor
public class OutletController {
    private OutletService outletService;

    @PostMapping(consumes = {"multipart/form-data"}, path = "/save")
    public ResponseEntity<StandardResponse> saveOutlet(@ModelAttribute OutletSaveRequestDTO outletSaveRequestDTO){
        OutletDTO outlet = outletService.saveOutlet(outletSaveRequestDTO);
        return new ResponseEntity<>(
                new StandardResponse(201,"Success",outlet),
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

    // get image by url
    @GetMapping("/url/{url}")
    public ResponseEntity<Resource> getImageByUrl(@PathVariable(value = "url") String url) {
        Resource imageResource = outletService.getImageByUrl(url);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageResource);
    }

    @PutMapping("status-by-id/{id}")
    public ResponseEntity<StandardResponse> updateOutletStatus(@PathVariable(value = "id") int outletID){
        OutletDTO outletDTO = outletService.updateOutletStatus(outletID);
        return new ResponseEntity<>(new StandardResponse(HttpStatus.OK.value(), "Successfully updated outlet status", outletDTO),
                HttpStatus.OK);
    }

    @GetMapping("incative-outlets")
    public ResponseEntity<StandardResponse> getIncativeOutlets(){
        List<ResponseGetOutletDTO> allOutlets = outletService.getIncativeOutlets();
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "fetch all outlets successfully!", allOutlets),
                HttpStatus.OK
        );
    }

    @GetMapping("all")
    public ResponseEntity<StandardResponse> getAllActiveAndInactiveOutlets(){
        List<ResponseGetOutletDTO> allOutlets = outletService.getAllActiveAndInactiveOutlets();
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "fetch all outlets successfully!", allOutlets),
                HttpStatus.OK
        );
    }
}
