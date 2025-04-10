package com.lloms.outlet_service.repository;

import com.lloms.outlet_service.entity.OutletReturn;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ReturnRepository extends JpaRepository<OutletReturn, Integer> {
}
