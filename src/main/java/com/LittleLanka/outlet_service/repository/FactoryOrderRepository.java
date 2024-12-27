package com.LittleLanka.outlet_service.repository;

import com.LittleLanka.outlet_service.entity.FactoryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface FactoryOrderRepository extends JpaRepository <FactoryOrder, Integer> {
}
