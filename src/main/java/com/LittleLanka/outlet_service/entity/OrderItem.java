package com.LittleLanka.outlet_service.entity;

import com.LittleLanka.outlet_service.dto.enums.OrderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OrderItemID;

    @Column(nullable = false)
    private int OrderID;

    @Column(nullable = false)
    private int ProductID;

    @Column(nullable = false)
    private double Quantity;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @ManyToOne
    @JoinColumn(name = "customer-order_id", nullable = false)
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "factory-order_id", nullable = false)
    private FactoryOrder factoryOrder;
}
