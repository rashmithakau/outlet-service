package com.lloms.outlet_service.entity;

import com.lloms.outlet_service.dto.response.ReturnItemIdDTO;
import com.lloms.outlet_service.enums.OutletReturnStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutletReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer returnId;

    @Column(nullable = false)
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    private OutletReturnStatus outletReturnStatus;

    @ManyToOne
    @JoinColumn(name = "outlet_id")
    private Outlet outlet;

    @OneToMany(mappedBy = "outletReturn", cascade = CascadeType.ALL)
    private List<ReturnItem> returnItems;

}
