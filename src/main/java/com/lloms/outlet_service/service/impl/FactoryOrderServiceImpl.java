package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.FactoryOrderItemDTO;
import com.lloms.outlet_service.dto.request.FactoryOrderRequestDTO;
import com.lloms.outlet_service.entity.FactoryOrder;
import com.lloms.outlet_service.entity.FactoryOrderItem;
import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.repository.FactoryOrderRepository;
import com.lloms.outlet_service.repository.OutletRepository;
import com.lloms.outlet_service.service.FactoryOrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FactoryOrderServiceImpl implements FactoryOrderService {
    private final FactoryOrderRepository factoryOrderRepository;
    private final OutletRepository outletRepository;
    private ModelMapper modelMapper;

    public void saveFacOrder(FactoryOrderRequestDTO facOrdReq) {
        Outlet outlet = outletRepository.findById(facOrdReq.getOutletId()).get();

        FactoryOrder facOrder = modelMapper.map(facOrdReq, FactoryOrder.class);
        facOrder.setOutlet(outlet);

        List<FactoryOrderItem> items=new ArrayList<>();
        for (FactoryOrderItemDTO item:facOrdReq.getItems()) {
            FactoryOrderItem facOrderItem = modelMapper.map(item, FactoryOrderItem.class);
            items.add(facOrderItem);
        }

        facOrder.setFactoryOrderItems(items);
        factoryOrderRepository.save(facOrder);


    }
}
