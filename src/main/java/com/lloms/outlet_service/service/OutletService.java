package com.lloms.outlet_service.service;

import com.lloms.outlet_service.dto.request.OutletSaveRequestDTO;

public interface OutletService {
    String saveOutlet(OutletSaveRequestDTO outletSaveRequestDTO);
}
