package com.lloms.outlet_service.repository;

import com.lloms.outlet_service.entity.FactoryOrder;
import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.enums.FactoryOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface FactoryOrderRepository extends JpaRepository <FactoryOrder, Integer> {
    List<FactoryOrder> getFactoryOrderByStatus(FactoryOrderStatus status);

    List<FactoryOrder> getFactoryOrderByFacOrderId(Integer facOrderId);

    List<FactoryOrder> getFactoryOrderByOutlet(Outlet outlet);
}
