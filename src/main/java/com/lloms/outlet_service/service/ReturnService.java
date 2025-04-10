package com.lloms.outlet_service.service;

import com.lloms.outlet_service.dto.OutletReturnDTO;
import com.lloms.outlet_service.enums.OutletReturnStatus;
import org.modelmapper.internal.bytebuddy.asm.Advice;

import java.util.List;

public interface ReturnService {
    void saveReturn (OutletReturnDTO returnDTO);

    void updateReturnStatus(Integer returnId, OutletReturnStatus status);

    List<OutletReturnDTO> getAllReturns();

    List<OutletReturnDTO> getAllReturnsByStatus(OutletReturnStatus status);

    List<OutletReturnDTO> getAllReturnsByOutletId(Integer outletId);

    List<OutletReturnDTO> getAllReturnsNotPending();
}
