package com.LittleLanka.outlet_service.entity;

import com.LittleLanka.outlet_service.dto.enums.CustomerOrderStatus;
import com.LittleLanka.outlet_service.dto.enums.FactoryOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FactoryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private Time orderTime;

    @Column(nullable = false)
    private FactoryOrderStatus status;

    @ManyToOne
    @JoinColumn(name = "outlet_id",nullable = false)
    private Outlet outlet;

    @OneToMany(mappedBy = "factoryOrder")
    private List<OrderItem> orderItem;
}