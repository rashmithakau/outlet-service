package com.lloms.outlet_service.dto.request;

import com.lloms.outlet_service.entity.CustomerOrder;
import com.lloms.outlet_service.entity.FactoryOrder;
import com.lloms.outlet_service.entity.OutletReturn;
import com.lloms.outlet_service.enums.OutletStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutletSaveRequestDTO {
    private String outletName;
    private String location;
    private int userId;
    private OutletStatus status;
}
