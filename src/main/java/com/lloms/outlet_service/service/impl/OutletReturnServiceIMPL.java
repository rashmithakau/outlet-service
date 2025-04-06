package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.OutletReturnDTO;
import com.lloms.outlet_service.dto.response.ReturnItemIdDTO;
import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.entity.OutletReturn;
import com.lloms.outlet_service.entity.ReturnItem;
import com.lloms.outlet_service.exception.NotFoundException;
import com.lloms.outlet_service.repository.OutletRepository;
import com.lloms.outlet_service.repository.OutletReturnRepository;
import com.lloms.outlet_service.service.OutletReturnService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutletReturnServiceIMPL implements OutletReturnService {

    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private OutletReturnRepository outletReturnRepository;

    @Autowired
    private ModelMapper modelMapper;

    public String saveOutletReturn(OutletReturnDTO outletReturnDTO) {
        List<ReturnItem> returnItems = new ArrayList<>();
        outletReturnDTO.getReturnItems().forEach(item -> {
            ReturnItem r = modelMapper.map(item, ReturnItem.class);
            r.setQuantity(item.getQuantity());
        });

        OutletReturn outletReturn = modelMapper.map(outletReturnDTO, OutletReturn.class);
        outletReturn.setReturnItems(returnItems);
        Outlet outlet = outletRepository.getReferenceById(outletReturnDTO.getOutletID());
        outletReturn.setOutlet(outlet);
        outletReturnRepository.save(outletReturn);
        return "Success";
    }

    @Override
    public List<ReturnItemIdDTO> findAllItemsId(Integer returnId) {
        if(returnId != null) {
            List<ReturnItemIdDTO> returnItemIdDTOS = new ArrayList<>();
            List<Integer> returnItemIds = outletReturnRepository.findReturnItemIdAndQuantityByReturnId(returnId);
            for (Integer id : returnItemIds) {
                ReturnItemIdDTO dto = new ReturnItemIdDTO();
                dto.setReturnItemId(id);
                returnItemIdDTOS.add(dto);
            }
            return returnItemIdDTOS;
        }
        else{
            throw new NotFoundException("Id's not found");
        }
    }

}
