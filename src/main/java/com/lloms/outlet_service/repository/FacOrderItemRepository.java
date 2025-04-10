package com.lloms.outlet_service.repository;

import com.lloms.outlet_service.entity.FactoryOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface FacOrderItemRepository extends JpaRepository<FactoryOrderItem,Integer> {
    List<FactoryOrderItem> getFactoryOrderItemsByFactoryOrder_FacOrderId(Integer factoryOrderFacOrderId);
}
