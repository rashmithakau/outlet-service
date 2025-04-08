package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.OutletReturnDTO;
import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.entity.OutletReturn;
import com.lloms.outlet_service.entity.ReturnItem;
import com.lloms.outlet_service.repository.OutletRepository;
import com.lloms.outlet_service.repository.OutletReturnRepository;
import com.lloms.outlet_service.service.ReturnService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReturnServiceImpl implements ReturnService {

    private final OutletReturnRepository outletReturnRepository;
    private final ModelMapper modelMapper;
    private final OutletRepository outletRepository;

    @Override
    @Transactional
    public void saveReturn(OutletReturnDTO returnDTO) {
        // Fetch the outlet safely
        Outlet outlet = outletRepository.findById(returnDTO.getOutletId())
                .orElseThrow(() -> new RuntimeException("Outlet not found"));

        // Map OutletReturnDTO to OutletReturn
        OutletReturn outletReturn = modelMapper.map(returnDTO, OutletReturn.class);
        outletReturn.setOutlet(outlet);

        // Map and associate ReturnItems using stream for cleaner code
        List<ReturnItem> returnItems = returnDTO.getReturnItems().stream()
                .map(dto -> {
                    ReturnItem item = modelMapper.map(dto, ReturnItem.class);
                    item.setOutletReturn(outletReturn);
                    return item;
                }).collect(Collectors.toList());

        outletReturn.setReturnItems(returnItems); // Set the list of return items

        // Save OutletReturn with return items (CascadeType.ALL handles saving items)
       outletReturnRepository.save(outletReturn);
    }

}
