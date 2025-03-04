package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.CustomerOrderItemDTO;
import com.lloms.outlet_service.dto.request.CustomerOrderRequestDTO;
import com.lloms.outlet_service.entity.*;
import com.lloms.outlet_service.repository.CusOrderItemRepository;
import com.lloms.outlet_service.repository.CustomerOrderRepository;
import com.lloms.outlet_service.repository.OutletRepository;
import com.lloms.outlet_service.service.CustomerOrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;
    private final CusOrderItemRepository cusOrderItemRepository;
    private final OutletRepository outletRepository;
    private ModelMapper modelMapper;

    public void saveFacOrder(CustomerOrderRequestDTO cusOrdReq) {
        Outlet outlet = outletRepository.findById(cusOrdReq.getOutletId()).get();

        CustomerOrder customerOrder = modelMapper.map(cusOrdReq, CustomerOrder.class);
        customerOrder.setOutlet(outlet);

        CustomerOrder savedCusOrder=customerOrderRepository.save(customerOrder);

        List<CustomerOrderItem> items=new ArrayList<>();
        for (CustomerOrderItemDTO item:cusOrdReq.getItems()) {
            CustomerOrderItem cusOrderItem = modelMapper.map(item, CustomerOrderItem.class);
            cusOrderItem.setCustomerOrder(savedCusOrder);
            items.add(cusOrderItem);
        }

        cusOrderItemRepository.saveAll(items);
    }

}
