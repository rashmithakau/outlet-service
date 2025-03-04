package com.lloms.outlet_service.service;

import com.lloms.outlet_service.dto.request.CustomerOrderRequestDTO;

public interface CustomerOrderService {
     void saveFacOrder(CustomerOrderRequestDTO cusOrdReq);
}
