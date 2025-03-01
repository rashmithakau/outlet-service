package com.lloms.outlet_service.entity;

import com.lloms.outlet_service.enums.OutletReturnStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutletReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer returnId;

    @Column(nullable = false)
    private Date returnDate;

    @Column(nullable = false)
    private Time returnTime;

    @Enumerated(EnumType.STRING)
    private OutletReturnStatus outletReturnStatus;

    @Column(nullable = false)
    private String outletName;

    @ManyToOne
    @JoinColumn(name = "outlet_id")
    private Outlet outlet;

}
