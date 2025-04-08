package com.lloms.outlet_service.repository;

import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.entity.OutletReturn;
import com.lloms.outlet_service.enums.OutletReturnStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OutletReturnRepository extends JpaRepository<OutletReturn, Integer> {
    @Query("SELECT r FROM OutletReturn r JOIN FETCH r.returnItems")
    List<OutletReturn> findAllWithItems();

    OutletReturn findByreturnId(Integer returnId);


    @Query("SELECT o FROM OutletReturn o LEFT JOIN FETCH o.returnItems WHERE o.outletReturnStatus = :status")
    List<OutletReturn> findAllByOutletReturnStatus(@Param("status") OutletReturnStatus status);

    List<OutletReturn> getAllByOutlet(Outlet outlet);

    @Query("SELECT r FROM OutletReturn r JOIN FETCH r.returnItems WHERE r.outlet = :outlet")
    List<OutletReturn> getAllByOutletWithItems(@Param("outlet") Outlet outlet);

    List<OutletReturn> getAllByOutletReturnStatusContains(OutletReturnStatus outletReturnStatus);

    List<OutletReturn> getAllByOutletReturnStatusIn(List<OutletReturnStatus> statuses);

    @Query("SELECT r FROM OutletReturn r JOIN FETCH r.returnItems WHERE r.outletReturnStatus != 'PENDING'")
    List<OutletReturn> findReturnsWithItemsNotPending();

}
