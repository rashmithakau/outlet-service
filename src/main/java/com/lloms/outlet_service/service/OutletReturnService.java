package com.lloms.outlet_service.service;

import com.lloms.outlet_service.dto.OutletReturnDTO;
import com.lloms.outlet_service.dto.response.ReturnItemIdDTO;

import java.util.List;

public interface OutletReturnService {

    String saveOutletReturn(OutletReturnDTO outletReturnDTO);

    List<ReturnItemIdDTO> findAllItemsId(Integer returnId);
}
