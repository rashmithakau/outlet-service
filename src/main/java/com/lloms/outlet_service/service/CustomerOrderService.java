package com.lloms.outlet_service.service;

import com.lloms.outlet_service.dto.CustomerOrderItemDTO;
import com.lloms.outlet_service.dto.request.CustomerOrderRequestDTO;
import com.lloms.outlet_service.dto.response.CusOrderResponseDTO;

import java.util.List;

public interface CustomerOrderService {
     void saveCusOrder(CustomerOrderRequestDTO cusOrdReq);

     List<CusOrderResponseDTO> findAllByOutletId(int outletId);

     List<CustomerOrderItemDTO> getCusOrderItems(Integer cusOrId);

     List<CusOrderResponseDTO> findAllByOutletIdAndDate(int outletId, String date);

     List<CusOrderResponseDTO> findAllByOutletIdAndMonthAndDate(int outletId, int year, int month);
}
