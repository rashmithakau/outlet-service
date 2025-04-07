package com.lloms.outlet_service.repository;

import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.enums.OutletStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OutletRepository extends JpaRepository <Outlet, Integer> {
    List<Outlet> findAllByStatusEquals(OutletStatus outletStatus);

}
