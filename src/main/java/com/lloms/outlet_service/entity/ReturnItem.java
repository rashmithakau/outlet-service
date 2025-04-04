package com.lloms.outlet_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer returnItemId;

    @Column(nullable = false)
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "return_id", nullable = false)
    private OutletReturn outletReturn;
}
