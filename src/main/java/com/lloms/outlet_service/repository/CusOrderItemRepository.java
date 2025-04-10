package com.lloms.outlet_service.repository;

import com.lloms.outlet_service.entity.CustomerOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CusOrderItemRepository extends JpaRepository <CustomerOrderItem, Integer> {
    List<CustomerOrderItem> getCustomerOrderItemsByCustomerOrder_CusOrderID(Integer cusOrId);
}
