package com.LittleLanka.outlet_service.service.impl;

import com.LittleLanka.outlet_service.dto.OutletDTO;
import com.LittleLanka.outlet_service.entity.Outlet;
import com.LittleLanka.outlet_service.repository.OutletRepository;
import com.LittleLanka.outlet_service.service.OutletService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutlerServiceImpl implements OutletService {

    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OutletDTO saveOutlet(OutletDTO outletDTO) {
        Outlet outlet = modelMapper.map(outletDTO, Outlet.class);
        Outlet outlet1 = outletRepository.save(outlet);
        OutletDTO outletDTO1 = modelMapper.map(outlet1, OutletDTO.class);
        return outletDTO1;
    }
}
