package com.lloms.outlet_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cusOrderItemId;

    @Column(nullable = false)
    private int productId;

    @Column(nullable = false)
    private double quantity;

    @Column(nullable = false)
    private double discountPerUnit;

    @ManyToOne
    @JoinColumn(name = "customer-order_id", nullable = false)
    private CustomerOrder customerOrder;

}
