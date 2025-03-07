package com.lloms.outlet_service.entity;

import com.lloms.outlet_service.enums.CustomerOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cusOrderID;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private CustomerOrderStatus status;

    @Column(nullable = true)
    private String customerName;

    @Column(nullable = true)
    private String customerPhone;

    @ManyToOne
    @JoinColumn(name = "outlet_id",nullable = false)
    private Outlet outlet;

    @OneToMany(mappedBy = "customerOrder",cascade = CascadeType.ALL)
    private List<CustomerOrderItem> customerOrderItem;
}
