package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.request.OutletSaveRequestDTO;
import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.repository.OutletRepository;
import com.lloms.outlet_service.service.OutletService;
import com.sun.jdi.request.DuplicateRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
}
