package com.lloms.outlet_service.service;

import com.lloms.outlet_service.dto.OutletReturnDTO;
import org.modelmapper.internal.bytebuddy.asm.Advice;

public interface ReturnService {
    void saveReturn (OutletReturnDTO returnDTO);
}
