package com.lloms.outlet_service.repository;

import com.lloms.outlet_service.entity.CustomerOrder;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface CustomerOrderRepository extends JpaRepository <CustomerOrder, Integer>{
    @Query("SELECT o FROM CustomerOrder o WHERE o.outlet.outletId = :outletId " + "AND FUNCTION('DATE', o.orderDate) = FUNCTION('DATE', :date)")
    List<CustomerOrder> findAllByOutletAndDate(@Param("outletId") int outletId, @Param("date") Date date);

    @Query("SELECT o FROM CustomerOrder o WHERE o.outlet.outletId = :outletId " + "AND YEAR(o.orderDate) = :year AND MONTH(o.orderDate) = :month")
    List<CustomerOrder> findAllByOutletAndYearAndMonth(@Param("outletId") int outletId, @Param("year") int year, @Param("month") int month);
}
