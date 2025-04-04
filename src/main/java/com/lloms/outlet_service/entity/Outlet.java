package com.lloms.outlet_service.entity;

import com.lloms.outlet_service.enums.OutletStatus;
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
public class Outlet{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer outletId;

    @Column(nullable = false,length = 50)
    private String outletName;

    @Column(nullable = false,length = 50)
    private String location;

    @Column(name = "image-url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private OutletStatus status;

    @OneToMany(mappedBy = "outlet")
    private List<CustomerOrder> customerOrder;

    @OneToMany(mappedBy = "outlet")
    private List<FactoryOrder> factoryOrders;

    @OneToMany(mappedBy = "outlet")
    private List<OutletReturn> returns;

}
