package com.LittleLanka.outlet_service.entity;

import com.LittleLanka.outlet_service.dto.enums.OutletStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "outlet")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Outlet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int outletId;

    @Column(nullable = false,length = 50)
    private String outletName;

    @Column(nullable = false,length = 50)
    private String location;

    @Column(nullable = false,length = 50)
    private int userId;

    @Enumerated(EnumType.STRING)
    private OutletStatus status;

    @OneToMany(mappedBy = "outlet")
    private List<CustomerOrder> customerOrder;

    @OneToMany(mappedBy = "outlet")
    private List<FactoryOrder> factoryOrders;
}
