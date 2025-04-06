package com.lloms.outlet_service.repository;

import com.lloms.outlet_service.dto.response.ReturnItemIdDTO;
import com.lloms.outlet_service.entity.OutletReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OutletReturnRepository extends JpaRepository<OutletReturn, Integer> {
    List<Integer> findReturnItemIdByReturnId(Integer returnId);

    List<Integer> findReturnItemIdAndQuantityByReturnId(Integer returnId);
}
