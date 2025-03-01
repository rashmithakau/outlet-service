package com.lloms.outlet_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FactoryOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facOrderItemId;

    @Column(nullable = false)
    private int productId;

    @Column(nullable = false)
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "factory-order_id", nullable = false)
    private FactoryOrder factoryOrder;
}
