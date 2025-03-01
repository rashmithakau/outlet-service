package com.lloms.outlet_service.entity;

import com.lloms.outlet_service.enums.CustomerOrderStatus;
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
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cusOrderID;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private Time orderTime;

    @Column(nullable = false)
    private CustomerOrderStatus status;

    private String CustomerName;

    private String CustomerPhone;

    @ManyToOne
    @JoinColumn(name = "outlet_id",nullable = false)
    private Outlet outlet;

    @OneToMany(mappedBy = "customerOrder")
    private List<CustomerOrderItem> customerOrderItem;
}
