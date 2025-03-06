package com.lloms.outlet_service.service;

import com.lloms.outlet_service.dto.request.OutletSaveRequestDTO;
import com.lloms.outlet_service.dto.request.RequestUpdateOutletDTO;
import com.lloms.outlet_service.dto.response.ResponseGetOutletDTO;

import java.util.List;

public interface OutletService {
    String saveOutlet(OutletSaveRequestDTO outletSaveRequestDTO);

    ResponseGetOutletDTO getOutletById(int outletId);

    List<ResponseGetOutletDTO> getAllOutlets();

    ResponseGetOutletDTO updateOutlet(RequestUpdateOutletDTO requestUpdateOutletDTO, int outletID);
}
