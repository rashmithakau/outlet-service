package com.LittleLanka.outlet_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository <OrderItemID, Integer> {
}