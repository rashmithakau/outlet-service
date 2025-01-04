package com.LittleLanka.outlet_service.entity;

import com.LittleLanka.outlet_service.dto.enums.OrderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cusOrderItemId;

    @Column(nullable = false)
    private int productId;

    @Column(nullable = false)
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "customer-order_id", nullable = false)
    private CustomerOrder customerOrder;

}
