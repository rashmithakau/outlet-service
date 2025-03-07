package com.lloms.outlet_service.service;

import com.lloms.outlet_service.dto.request.FactoryOrderRequestDTO;
import com.lloms.outlet_service.dto.response.FactoryOrderResDTO;
import com.lloms.outlet_service.enums.FactoryOrderStatus;

import java.util.List;

public interface FactoryOrderService {
     void saveFacOrder(FactoryOrderRequestDTO facOrdReq);

     List<FactoryOrderResDTO> getFacOrderByStatus(FactoryOrderStatus status);
}
