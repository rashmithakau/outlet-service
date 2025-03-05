package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.request.OutletSaveRequestDTO;
import com.lloms.outlet_service.dto.request.RequestUpdateOutletDTO;
import com.lloms.outlet_service.dto.response.ResponseGetOutletDTO;
import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.exception.NotFoundException;
import com.lloms.outlet_service.repository.OutletRepository;
import com.lloms.outlet_service.service.OutletService;
import com.lloms.outlet_service.util.StandardResponse;
import com.sun.jdi.request.DuplicateRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class OutletServiceIMPL implements OutletService {
    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String saveOutlet(OutletSaveRequestDTO outletSaveRequestDTO) {
        Outlet outlet = modelMapper.map(outletSaveRequestDTO,Outlet.class);
        if(!outletRepository.existsById(outlet.getOutletId())){
            outletRepository.save(outlet);
            return outlet.getOutletId()+" Saved Successfully";
        }else{
            throw new DuplicateKeyException("Already Added");
        }
    }

    @Override
    public ResponseGetOutletDTO getOutletById(int outletId) {
        if(outletRepository.existsById(outletId)){
            Outlet outlet = outletRepository.getReferenceById(outletId);
            return modelMapper.map(outlet,ResponseGetOutletDTO.class);
        }else {
            throw new NotFoundException("No outlet found!");
        }
    }

    @Override
    public List<ResponseGetOutletDTO> getAllOutlets() {
        List<Outlet> outlets = outletRepository.findAll();
        if(!outlets.isEmpty()){
            return outlets.stream()
                    .map(outlet -> modelMapper.map(outlet,ResponseGetOutletDTO.class))
                    .toList();
        }else{
            throw new NotFoundException("No Outlets!");
        }
    }

    @Override
    public ResponseGetOutletDTO updateOutlet(RequestUpdateOutletDTO requestUpdateOutletDTO, int outletID) {
        Optional<Outlet> outletToUpdate = outletRepository.findById(outletID);
        if(outletToUpdate.isPresent()){
            Outlet outlet = outletToUpdate.get();

            //update only userId, status and imageUrl
            outlet.setUserId(requestUpdateOutletDTO.getUserId());
            outlet.setStatus(requestUpdateOutletDTO.getStatus());
            outlet.setImageUrl(requestUpdateOutletDTO.getImageUrl());

            //to save
            Outlet updatedOutlet = outletRepository.save(outlet);

            return modelMapper.map(updatedOutlet,ResponseGetOutletDTO.class);
        }else{
            throw new NotFoundException("Outlet not found with Id "+ outletID);
        }
    }


}
