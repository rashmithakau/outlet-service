package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.CustomerOrderItemDTO;
import com.lloms.outlet_service.dto.FactoryOrderItemDTO;
import com.lloms.outlet_service.dto.request.CustomerOrderRequestDTO;
import com.lloms.outlet_service.entity.*;
import com.lloms.outlet_service.repository.CusOrderItemRepository;
import com.lloms.outlet_service.repository.CustomerOrderRepository;
import com.lloms.outlet_service.repository.OutletRepository;
import com.lloms.outlet_service.service.CustomerOrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;
    private final CusOrderItemRepository cusOrderItemRepository;
    private final OutletRepository outletRepository;
    private ModelMapper modelMapper;

    @Transactional
    public void saveFacOrder(CustomerOrderRequestDTO cusOrdReq) {
        // Fetch the outlet safely
        Outlet outlet = outletRepository.findById(cusOrdReq.getOutletId())
                .orElseThrow(() -> new RuntimeException("Outlet not found"));

        // Map CustomerOrderRequestDTO to CustomerOrder
        CustomerOrder customerOrder = modelMapper.map(cusOrdReq, CustomerOrder.class);
        customerOrder.setOutlet(outlet);

        // Map and associate CustomerOrderItems using stream for cleaner code
        List<CustomerOrderItem> orderItems = cusOrdReq.getItems().stream()
                .map(dto -> {
                    CustomerOrderItem item = modelMapper.map(dto, CustomerOrderItem.class);
                    item.setCustomerOrder(customerOrder);
                    return item;
                }).collect(Collectors.toList());

        customerOrder.setStatus(cusOrdReq.getStatus());
        customerOrder.setCustomerOrderItem(orderItems);

        // Save CustomerOrder with items (CascadeType.ALL handles saving items)
        customerOrderRepository.save(customerOrder);
    }


}
