package com.lloms.outlet_service.service;

import com.lloms.outlet_service.dto.OutletDTO;
import com.lloms.outlet_service.dto.request.OutletSaveRequestDTO;
import com.lloms.outlet_service.dto.request.RequestUpdateOutletDTO;
import com.lloms.outlet_service.dto.response.ResponseGetOutletDTO;
import org.springframework.core.io.Resource;

import java.util.List;

public interface OutletService {
    OutletDTO saveOutlet(OutletSaveRequestDTO outletSaveRequestDTO);

    ResponseGetOutletDTO getOutletById(int outletId);

    List<ResponseGetOutletDTO> getAllOutlets();

    ResponseGetOutletDTO updateOutlet(RequestUpdateOutletDTO requestUpdateOutletDTO, int outletID);

    Resource getImageByUrl(String url);

    OutletDTO updateOutletStatus(int outletID);

    List<ResponseGetOutletDTO> getIncativeOutlets();

    List<ResponseGetOutletDTO> getAllActiveAndInactiveOutlets();
}
