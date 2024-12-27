package com.LittleLanka.outlet_service.repository;

import com.LittleLanka.outlet_service.entity.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OutletRepository extends JpaRepository <Outlet, Integer> {
}
