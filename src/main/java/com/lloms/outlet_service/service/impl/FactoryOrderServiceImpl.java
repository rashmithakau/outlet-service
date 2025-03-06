package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.FactoryOrderItemDTO;
import com.lloms.outlet_service.dto.request.FactoryOrderRequestDTO;
import com.lloms.outlet_service.entity.FactoryOrder;
import com.lloms.outlet_service.entity.FactoryOrderItem;
import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.repository.FacOrderItemRepository;
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
    private final FacOrderItemRepository facOrderItemRepository;
    private final OutletRepository outletRepository;
    private ModelMapper modelMapper;

    public void saveFacOrder(FactoryOrderRequestDTO facOrdReq) {
        // Fetch the outlet safely
        Outlet outlet = outletRepository.findById(facOrdReq.getOutletId())
                .orElseThrow(() -> new RuntimeException("Outlet not found"));

        // Map FactoryOrderRequestDTO to FactoryOrder
        FactoryOrder facOrder = modelMapper.map(facOrdReq, FactoryOrder.class);
        facOrder.setOutlet(outlet);

        // Map and associate FactoryOrderItems
        List<FactoryOrderItem> orderItems = new ArrayList<>();
        for (FactoryOrderItemDTO factoryOrderItemDTO : facOrdReq.getItems()) {
            FactoryOrderItem orderItem = modelMapper.map(factoryOrderItemDTO, FactoryOrderItem.class);
            orderItem.setFactoryOrder(facOrder);  // Associate with FactoryOrder
            orderItems.add(orderItem);
        }

        facOrder.setFactoryOrderItems(orderItems);

        // Save FactoryOrder with items (CascadeType.ALL handles saving items)
        factoryOrderRepository.save(facOrder);
    }

}
