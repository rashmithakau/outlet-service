package com.LittleLanka.outlet_service.repository;

import com.LittleLanka.outlet_service.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CustomerOrderRepository extends JpaRepository <CustomerOrder, Integer>{
}
